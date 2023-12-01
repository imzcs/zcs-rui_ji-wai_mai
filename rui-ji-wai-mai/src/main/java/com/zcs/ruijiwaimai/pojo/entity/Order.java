package com.zcs.ruijiwaimai.pojo.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zcs.ruijiwaimai.constant.OrderState;
import com.zcs.ruijiwaimai.constant.PaymentType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@TableName("tb_order")
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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    @TableField("is_deleted")
    private Byte deleted;
}