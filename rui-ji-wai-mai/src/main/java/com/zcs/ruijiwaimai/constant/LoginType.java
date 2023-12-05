package com.zcs.ruijiwaimai.constant;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum LoginType implements IEnum<Integer> {
    PHONE_AND_CODE(11),
    PHONE_AND_PASSWORD(21),
    WECHAT(31);

    private int value;

    LoginType(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
