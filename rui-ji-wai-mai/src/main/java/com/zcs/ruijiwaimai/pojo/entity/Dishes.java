package com.zcs.ruijiwaimai.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class Dishes {
    private Long id;
    private Long merchantId;
    private String name;
    private String introduce;
    private String picture;
    private Integer price;
    private Integer sales;
    private Dishes state;
    private Byte label;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}