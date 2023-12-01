package com.zcs.ruijiwaimai.pojo.entity;

import cn.hutool.core.annotation.Link;
import com.baomidou.mybatisplus.annotation.*;
import com.zcs.ruijiwaimai.constant.LoginType;
import com.zcs.ruijiwaimai.constant.UserRole;
import lombok.*;

import java.lang.annotation.Target;
import java.time.LocalDateTime;

@Data
@TableName("tb_user")
public class User {
    private Long id;
    private String account;
    private String password;
    private LoginType loginType;
    private UserRole role;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    @TableField("is_deleted")
    private Byte deleted;
}