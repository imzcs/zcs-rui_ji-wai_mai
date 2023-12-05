package com.zcs.ruijiwaimai.controller;

import com.zcs.ruijiwaimai.constant.LoginType;
import com.zcs.ruijiwaimai.constant.UserRole;
import com.zcs.ruijiwaimai.pojo.dto.Result;
import com.zcs.ruijiwaimai.service.inter.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/code")
    public Result code(@RequestParam("phone") String phone) {
        return userService.code(phone);
    }


    @PostMapping("/login")
    public Result login(
            @RequestParam(value = "type", defaultValue = "PHONE_AND_CODE") LoginType loginType,
            @RequestParam("role") UserRole role,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "password", required = false) String password
    ) {
        return userService.login(loginType, role, phone, code, password);
    }

    @PostMapping("/password")
    public Result modifyPassword(@RequestParam(value = "old", required = false) String oldPassword, @RequestParam("new") String newPassword) {
        return userService.modifyPassword(oldPassword, newPassword);
    }

}
