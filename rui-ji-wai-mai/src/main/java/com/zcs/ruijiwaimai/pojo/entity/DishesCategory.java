package com.zcs.ruijiwaimai.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class DishesCategory {
    private Long id;
    private Long merchantId;
    private String name;
    private Byte state;
    private Byte label;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}