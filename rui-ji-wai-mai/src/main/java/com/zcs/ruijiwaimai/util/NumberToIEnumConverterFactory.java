package com.zcs.ruijiwaimai.util;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

@Slf4j
public class NumberToIEnumConverterFactory implements ConverterFactory<String, IEnum<Integer>> {

    @Override
    public <T extends IEnum<Integer>> Converter<String, T> getConverter(Class<T> targetType) {
        return source -> {
            // 不是数字（是字符串）不支持转换，交给StringToEnumConverterFactory
            if (!NumberUtil.isNumber(source)) {
                throw new IllegalArgumentException();
            }
            // 是数字
            for (T value : targetType.getEnumConstants()) {
                if (value.getValue().toString().equals(source)) {
                    log.debug("convert [{}] to [{}] success", source, value);
                    return value;
                }
            }
            log.warn("convert [{}] to [UserRole] error", source);
            return null;
        };
    }
}
