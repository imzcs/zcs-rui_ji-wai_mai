package com.zcs.ruijiwaimai.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcs.ruijiwaimai.constant.LoginType;
import com.zcs.ruijiwaimai.constant.UserRole;
import com.zcs.ruijiwaimai.mapper.UserMapper;
import com.zcs.ruijiwaimai.pojo.dto.Result;
import com.zcs.ruijiwaimai.pojo.dto.UserDTO;
import com.zcs.ruijiwaimai.pojo.entity.User;
import com.zcs.ruijiwaimai.pojo.entity.UserInfo;
import com.zcs.ruijiwaimai.service.inter.UserInfoService;
import com.zcs.ruijiwaimai.service.inter.UserService;
import com.zcs.ruijiwaimai.util.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.zcs.ruijiwaimai.constant.RedisConstant.*;
import static com.zcs.ruijiwaimai.util.RegexpTool.isInValidPhoneNumber;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserInfoService userInfoService;

    @Override
    public Result code(String phone) {
        // 检查手机号
        if (isInValidPhoneNumber(phone)) {
            log.warn("The format of the mobile phone number [{}] is incorrect.", phone);
            return Result.fail("手机号格式不正确");
        }
        // 获取验证码
        String code = RandomUtil.randomNumbers(6);
        // 保存验证码
        String key = LOGIN_CODE_KEY_PREFIX + phone;
        stringRedisTemplate.opsForValue().set(key, code, LOGIN_CODE_KEY_TTL, TimeUnit.MINUTES);
        // 发生验证码
        sendCode(code);
        return Result.ok();
    }

    @Transactional
    @Override
    public Result login(LoginType loginType, UserRole role, String phone, String code, String password) {
        // 1.检查请求参数
        String checkResult = paramCheck(loginType, role, phone, code, password);
        if(checkResult != null) {
            return Result.fail(checkResult);
        }
        // 验证码登录
        if (loginType == LoginType.PHONE_AND_CODE) {
            return loginByCode(phone, code, role);
        }
        // 密码登录
        else if (loginType == LoginType.PHONE_AND_PASSWORD) {
            return loginByPassword(phone, password);
        }
        // 微信登录
        else if (loginType == LoginType.WECHAT) {
            // return loginByWechat();
        }
        return Result.fail("登录方式错误");
    }

    @Override
    @Transactional
    public Result modifyPassword(String oldPassword, String newPassword) {
        // 是否登录
        UserDTO userDTO = UserHolder.getUser();
        if (userDTO == null) {
            return Result.fail("未登录");
        }
        // 密码格式是否正确
        // if (isInValidPassword(newPassword)) {
        //     return Result.fail("密码格式不正确");
        // }
        // 账号是否存在
        User user = getById(userDTO.getUserId());
        if (user == null) {
            return Result.fail("账号不存在");
        }
        // 已设置密码，校验密码是否正确
        String password = user.getPassword();
        if (StrUtil.isNotEmpty(password)) {
            if (!DigestUtil.bcryptCheck(oldPassword, password)) {
                return Result.fail("旧密码错误");
            }
        }
        // 设置新密码
        boolean isOk = update().set("password", DigestUtil.bcrypt(newPassword)).eq("id", user.getId()).update();
        if (isOk) {
            return Result.ok();
        } else {
            return Result.fail("修改失败");
        }
    }

    private Result loginByCode(String phone, String code, UserRole role) {
        // 2.1校验验证码
        String key = LOGIN_CODE_KEY_PREFIX + phone;
        String cacheCode = stringRedisTemplate.opsForValue().getAndDelete(key);
        if (StrUtil.isEmpty(cacheCode) || !cacheCode.equals(code)) {
            // 2.2验证码错误
            log.warn("The validation code [{}] is incorrect.", code);
            return Result.fail("验证码错误");
        }
        // 3.验证码正确
        // 查询账号是否存在
        User user = getOne(Wrappers.<User>query().eq("account", phone).eq("login_type", LoginType.PHONE_AND_CODE));
        if (user == null) {
            // 3.1 不存在，创建新账号
            user = createNewAccount(phone, role);
        }
        // 3.2 存在，生成token，保存到redis中，将token返回
        String token = generateAndSaveToken(user);
        return Result.ok(token);
    }

    private Result loginByPassword(String phone, String password) {
        String encryptedPassword = DigestUtil.bcrypt(password);
        User user = getOne(Wrappers.<User>query().eq("account", phone).eq("login_type", LoginType.PHONE_AND_CODE));
        if (user == null ||
            StrUtil.isEmpty(user.getPassword()) ||
            !DigestUtil.bcryptCheck(password, user.getPassword())) {
            // 3.1 不存在，登录失败
            return Result.fail("账号或密码错误");
        }
        // 3.2 存在，生成token，保存到redis中，将token返回
        String token = generateAndSaveToken(user);
        return Result.ok(token);
    }

    private String paramCheck (LoginType loginType, UserRole role, String phone, String code, String password) {
        if (loginType == null) {
            log.error("The login type is [null].");
            return "登录方式错误";
        }
        // 1.1 检查登录角色
        if (role == null) {
            log.error("The login role is [null].");
            return "登录角色错误";
        }
        // 1.1检查手机号
        if (isInValidPhoneNumber(phone)) {
            log.warn("The format of the mobile phone number [{}] is incorrect.", phone);
            return "手机号格式不正确";
        }
        // 1.2检查验证码
        if (loginType == LoginType.PHONE_AND_CODE && StrUtil.isEmpty(code)) {
            log.warn("The format of the validation code [{}] is incorrect.", code);
            return "验证码格式不正确";
        }
        // 1.3检查密码
        else if (loginType == LoginType.PHONE_AND_PASSWORD && StrUtil.isEmpty(password)) {
            log.warn("The format of the password [{}] is incorrect.", password);
            return "密码格式不正确";
        }
        return null;
    }


    private String generateAndSaveToken(User user) {
        // 1.获取user信息
        if (user == null || user.getId() == null) {
            return null;
        }
        Long userId = user.getId();
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>query().eq("user_id", userId));
        UserDTO userDTO = new UserDTO(userId, userInfo.getId(), userInfo.getUid(), user.getLoginType(), user.getRole());
        String userDTOStr = JSONUtil.toJsonStr(userDTO, JSONConfig.create().setIgnoreNullValue(true));
        // 2.生成token
        String token = RandomUtil.randomString(20);
        // 3.保存token和user信息
        String key = LOGIN_TOKEN_KEY_PREFIX + token;
        stringRedisTemplate.opsForValue().set(key, userDTOStr, LOGIN_TOKEN_KEY_TTL, TimeUnit.MINUTES);
        return token;
    }

    private User createNewAccount(String phone, UserRole role) {
        // 创建账号
        User user = new User();
        user.setAccount(phone);
        user.setLoginType(LoginType.PHONE_AND_CODE);
        user.setRole(role);
        boolean isOk = save(user);
        if (!isOk) {
            log.debug("create account fail");
            throw new RuntimeException("创建账号失败");
        }

        // 创建账号信息
        // 生成UID
        long uid = IdUtil.getSnowflake().nextId();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setUid(uid);
        userInfo.setPhone(phone);
        isOk = userInfoService.save(userInfo);
        if (!isOk) {
            log.debug("create account fail");
            throw new RuntimeException("创建账号失败");
        }
        log.debug("create a new account, account: " + phone + " loginType: phone, role: customer");
        return user;
    }

    private void sendCode(String code) {
        log.info("send phone validation code: " + code);
    }
}
