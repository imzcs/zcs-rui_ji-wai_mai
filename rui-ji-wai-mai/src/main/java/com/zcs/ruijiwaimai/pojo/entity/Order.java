package com.zcs.ruijiwaimai.pojo.entity;


import com.zcs.ruijiwaimai.constant.OrderState;
import com.zcs.ruijiwaimai.constant.PaymentType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class Order {
    private Long id;
    private Long userId;
    private Long merchantId;
    private String content;
    private String remark;
    private Long shippingAddressId;
    private OrderState state;
    private PaymentType paymentType;
    private LocalDateTime orderTime;
    private LocalDateTime paymentTime;
    private LocalDateTime merchantAcceptTime;
    private LocalDateTime deliveryTime;
    private LocalDateTime finishTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}