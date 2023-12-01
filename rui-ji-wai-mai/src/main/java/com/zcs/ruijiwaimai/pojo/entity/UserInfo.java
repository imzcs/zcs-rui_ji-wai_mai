package com.zcs.ruijiwaimai.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zcs.ruijiwaimai.constant.Gender;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@TableName("tb_user_info")
public class UserInfo extends Model<UserInfo> {
    private Long id;
    private Long userId;
    private Long uid;
    private String username;
    private Gender gender;
    private String phone;
    private String profilePhoto;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    @TableField("is_deleted")
    private Byte deleted;
}