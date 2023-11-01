package com.zcs.ruijiwaimai.pojo.entity;

import cn.hutool.core.annotation.Link;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zcs.ruijiwaimai.constant.LoginType;
import com.zcs.ruijiwaimai.constant.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.annotation.Target;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
// @Builder
public class User {
    private Long id;
    private String account;
    private String password;
    private LoginType loginType;
    private UserRole role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}