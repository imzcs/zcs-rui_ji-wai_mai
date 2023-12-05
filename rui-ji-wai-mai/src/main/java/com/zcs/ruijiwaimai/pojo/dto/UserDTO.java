package com.zcs.ruijiwaimai.pojo.dto;

import com.zcs.ruijiwaimai.constant.LoginType;
import com.zcs.ruijiwaimai.constant.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private Long userInfoId;
    private Long uid;
    private LoginType loginType;
    private UserRole role;
}
