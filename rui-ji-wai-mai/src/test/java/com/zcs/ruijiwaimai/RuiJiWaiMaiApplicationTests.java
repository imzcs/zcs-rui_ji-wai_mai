package com.zcs.ruijiwaimai;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zcs.ruijiwaimai.mapper.UserInfoMapper;
import com.zcs.ruijiwaimai.pojo.entity.UserInfo;
import com.zcs.ruijiwaimai.service.impl.UserInfoServiceImpl;
import com.zcs.ruijiwaimai.service.inter.UserInfoService;
import com.zcs.ruijiwaimai.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@Slf4j
class RuiJiWaiMaiApplicationTests {


    @Resource
    UserInfoService userInfoService;

    @Test
    void contextLoads() {
        UserInfo userInfo = userInfoService.getById(1);
        System.out.println(userInfo);
        userInfoService.removeById(userInfo);
    }


}
