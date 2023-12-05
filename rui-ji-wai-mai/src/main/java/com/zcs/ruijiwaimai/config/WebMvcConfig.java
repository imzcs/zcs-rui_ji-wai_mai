package com.zcs.ruijiwaimai.config;

import com.zcs.ruijiwaimai.interceptor.RefreshLoginExpireInterceptor;
import com.zcs.ruijiwaimai.util.NumberToIEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new NumberToIEnumConverterFactory());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RefreshLoginExpireInterceptor(stringRedisTemplate))
                .addPathPatterns("/**")
                .order(1);
    }
}
