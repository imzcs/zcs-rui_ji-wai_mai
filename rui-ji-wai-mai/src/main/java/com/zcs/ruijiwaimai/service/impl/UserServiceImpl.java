package com.zcs.ruijiwaimai.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcs.ruijiwaimai.constant.LoginType;
import com.zcs.ruijiwaimai.constant.UserRole;
import com.zcs.ruijiwaimai.mapper.UserMapper;
import com.zcs.ruijiwaimai.pojo.dto.Result;
import com.zcs.ruijiwaimai.pojo.entity.User;
import com.zcs.ruijiwaimai.pojo.entity.UserInfo;
import com.zcs.ruijiwaimai.service.inter.UserInfoService;
import com.zcs.ruijiwaimai.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
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
            return Result.fail("手机号格式不正确");
        }
        // 获取验证码
        String code = RandomUtil.randomNumbers(6);
        // 保存验证码
        String key = LOGIN_CODE_KEY_PREFIX + phone;
        stringRedisTemplate.opsForValue().set(key, code, LOGIN_CODE_KEY_TTL, TimeUnit.SECONDS);
        // 发生验证码
        sendCode(code);
        return Result.ok();
    }

    @Transactional
    @Override
    public Result login(String phone, String code) {
        // 检查手机号
        if (isInValidPhoneNumber(phone)) {
            return Result.fail("手机号格式不正确");
        }
        // 检查验证码
        if (StrUtil.isEmpty(code)) {
            return Result.fail("验证码格式不正确");
        }
        // 校验验证码
        String key = LOGIN_CODE_KEY_PREFIX + phone;
        String cacheCode = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isEmpty(cacheCode) || !cacheCode.equals(code)) {
            return Result.fail("验证码错误");
        }
        // 查询账号是否存在
        User user = null;
        List<User> list = list(Wrappers.<User>query().eq("account", phone).eq("login_type", LoginType.PHONE));
        if (list == null || list.isEmpty()) {
            // 不存在，创建心账户
            user = createNewAccount(phone);
        } else {
            user = list.get(0);
        }
        // 存在，生成token，保存到redis中，将toekn返回
       String token = generateAndSaveToken(user);
        return Result.ok(token);
    }

    private String generateAndSaveToken(User user) {
        String token = RandomUtil.randomString(20);
        String key = LOGIN_TOKEN_KEY_PREFIX + token;
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>query().eq("user_id", user.getId()));
        String userInfoStr = JSONUtil.toJsonStr(userInfo, JSONConfig.create().setIgnoreNullValue(true));
        stringRedisTemplate.opsForValue().set(key, userInfoStr, LOGIN_TOKEN_KEY_TTL, TimeUnit.MINUTES);
        return token;
    }

    private User createNewAccount(String phone) {
        // 创建账号
        User user = new User();
        user.setAccount(phone);
        user.setLoginType(LoginType.PHONE);
        user.setRole(UserRole.CONSUMER);
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
        log.debug("发送验证码: " + code);
    }
}
