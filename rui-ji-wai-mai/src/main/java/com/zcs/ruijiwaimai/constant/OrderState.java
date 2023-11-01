package com.zcs.ruijiwaimai.constant;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum OrderState implements IEnum<Integer> {
    UNPAID(11),
    PAID(21),
    ACCESS(31),
    DISTRIBUTION(41),
    FINISH(51);

    private int value;

    OrderState(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
