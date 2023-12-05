package com.zcs.ruijiwaimai.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.zcs.ruijiwaimai.pojo.dto.UserDTO;
import com.zcs.ruijiwaimai.util.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.TimeUnit;

import static com.zcs.ruijiwaimai.constant.RedisConstant.LOGIN_TOKEN_KEY_PREFIX;
import static com.zcs.ruijiwaimai.constant.RedisConstant.LOGIN_TOKEN_KEY_TTL;

public class RefreshLoginExpireInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public RefreshLoginExpireInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String key = LOGIN_TOKEN_KEY_PREFIX + token;
            String userDTOStr = stringRedisTemplate.opsForValue().getAndExpire(key, LOGIN_TOKEN_KEY_TTL, TimeUnit.MINUTES);
            if (StrUtil.isNotEmpty(userDTOStr)) {
                UserDTO userDTO = JSONUtil.toBean(userDTOStr, JSONConfig.create().setIgnoreNullValue(true), UserDTO.class);
                UserHolder.set(userDTO);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.remove();
    }
}
