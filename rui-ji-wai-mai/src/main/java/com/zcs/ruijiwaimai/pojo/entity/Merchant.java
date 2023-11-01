package com.zcs.ruijiwaimai.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class Merchant {
    private Long id;
    private String name;
    private String introduce;
    private String logoPhoto;
    private String province;
    private String city;
    private String county;
    private String address;
    private String longitude;
    private String latitude;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}