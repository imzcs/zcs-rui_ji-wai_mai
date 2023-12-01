package com.zcs.ruijiwaimai.controller;

import com.zcs.ruijiwaimai.pojo.dto.Result;
import com.zcs.ruijiwaimai.service.inter.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/code")
    public Result code(String phone) {
        return userService.code(phone);
    }


    @PostMapping("/login")
    public Result login(String phone, String code) {
        return userService.login(phone, code);
    }

}
