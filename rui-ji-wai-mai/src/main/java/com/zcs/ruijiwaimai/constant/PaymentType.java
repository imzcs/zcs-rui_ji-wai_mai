package com.zcs.ruijiwaimai.constant;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum PaymentType implements IEnum<Integer> {
    ALIPAY(100), WECHAT(200);

    private int value;

    PaymentType(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
