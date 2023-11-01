package com.zcs.ruijiwaimai.constant;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum ShippingAddressLabel implements IEnum<Integer> {
    HOME(1), COMPANY(2), SCHOOL(3);

    private int value;

    ShippingAddressLabel(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
