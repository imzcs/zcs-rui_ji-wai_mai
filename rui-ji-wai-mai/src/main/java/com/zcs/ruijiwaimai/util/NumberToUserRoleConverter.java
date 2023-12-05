package com.zcs.ruijiwaimai.util;

import com.zcs.ruijiwaimai.constant.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;


// @Component
@Slf4j
public class NumberToUserRoleConverter implements Converter<String, UserRole> {

    @Override
    public UserRole convert(String source) {
        for (UserRole value : UserRole.values()) {
            if (value.getValue().toString().equals(source)) {
                log.debug("convert [{}] to [{}] success", source, value.name());
                return value;
            }
        }
        log.warn("convert [{}] to [UserRole] error", source);
        return null;
    }
}
