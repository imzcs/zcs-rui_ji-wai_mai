package com.zcs.ruijiwaimai.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class DishesAndCategoryRel {
    private Long id;
    private Long categoryId;
    private String dishes;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}