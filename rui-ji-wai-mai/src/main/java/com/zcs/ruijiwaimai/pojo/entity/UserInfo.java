package com.zcs.ruijiwaimai.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zcs.ruijiwaimai.constant.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
// @Builder
@Accessors(chain = true)
public class UserInfo extends Model<UserInfo> {
    private Long id;
    private Long userId;
    private Long uid;
    private String username;
    private Gender gender;
    private String phone;
    private String profilePhoto;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @TableField("is_deleted")
    private Byte deleted;
}