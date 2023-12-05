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
            @RequestParam(value = "type", defaultValue = "PHONE") LoginType loginType,
            @RequestParam("phone") String phone,
            @RequestParam("code") String code,
            @RequestParam("role") UserRole role
    ) {
        return userService.login(loginType, phone, code, role);
    }

}
