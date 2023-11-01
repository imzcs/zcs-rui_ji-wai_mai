package com.zcs.ruijiwaimai.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@ToString
@Getter
@Setter
public class MerchantStaff {
    private Long id;
    private Long merchantId;
    private Long userId;
    private Boolean isAdmin;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}