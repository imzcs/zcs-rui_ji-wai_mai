package com.zcs.ruijiwaimai.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcs.ruijiwaimai.constant.LoginType;
import com.zcs.ruijiwaimai.constant.UserRole;
import com.zcs.ruijiwaimai.pojo.dto.Result;
import com.zcs.ruijiwaimai.pojo.entity.User;

public interface UserService extends IService<User> {
    Result code(String phone);

    Result login(LoginType loginType, String phone, String code, UserRole role);

}
