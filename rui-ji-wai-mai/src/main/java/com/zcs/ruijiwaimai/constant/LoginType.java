package com.zcs.ruijiwaimai.constant;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum LoginType implements IEnum<Integer> {
    PHONE(11),
    EMAIL(21),
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
