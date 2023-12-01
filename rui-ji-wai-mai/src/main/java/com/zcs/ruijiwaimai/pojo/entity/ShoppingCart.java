package com.zcs.ruijiwaimai.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@TableName("tb_shopping_cart")
public class ShoppingCart {
    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @TableLogic
    @TableField("is_deleted")
    private Byte deleted;
}