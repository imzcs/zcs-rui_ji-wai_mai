package com.zcs.ruijiwaimai.pojo.entity;

import com.zcs.ruijiwaimai.constant.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
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
}