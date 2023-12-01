package com.zcs.ruijiwaimai.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zcs.ruijiwaimai.constant.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@TableName("tb_shopping_address")
public class ShippingAddress {
    private Long id;
    private Long userId;
    private String contact;
    private Gender gender;
    private String phone;
    private Byte label;
    private String province;
    private String city;
    private String county;
    private String address;
    private String longitude;
    private String latitude;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @TableLogic
    @TableField("is_deleted")
    private Byte deleted;
}