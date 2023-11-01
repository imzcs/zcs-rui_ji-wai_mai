package com.zcs.ruijiwaimai.constant;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum UserRole implements IEnum<Integer> {
    CONSUMER(0), BUSINESS_ADMIN(1), BUSINESS_STAFF(2);

    private int value;

    UserRole(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
