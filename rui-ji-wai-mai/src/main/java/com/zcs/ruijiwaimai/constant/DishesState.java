package com.zcs.ruijiwaimai.constant;


import com.baomidou.mybatisplus.annotation.IEnum;

public enum DishesState implements IEnum<Integer> {
    SELLING(0), OFF(10), SELL_OUT(1);

    private Integer value;

    DishesState(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
