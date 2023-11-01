package com.zcs.ruijiwaimai.constant;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum Gender implements IEnum<Integer> {
    UNKNOWN(0), MAN(1), WOMAN(2);

    private int value;

    Gender(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
