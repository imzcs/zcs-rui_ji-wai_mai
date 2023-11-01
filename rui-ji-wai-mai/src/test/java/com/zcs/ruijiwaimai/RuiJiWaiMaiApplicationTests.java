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

    // @Resource
    // UserService userService;

    @Resource
    UserInfoMapper mapper;


    // @Resource
    // UserInfoServiceImpl service;

    @Test
    void contextLoads() {
        // User user = new User();
        // user.setAccount(IdUtil.fastSimpleUUID());
        // user.setCreateTime(LocalDateTime.now());
        // user.setUpdateTime(LocalDateTime.now());
        // user.setLoginType(LoginType.PHONE_AND_PASSWORD);
        // user.setRole(UserRole.BUSINESS_ADMIN);
        // long start = System.nanoTime();
        // userMapper.insert(user);
        // log.debug("cast ===> " + (System.nanoTime() - start) + "ms");


        // userMapper.deleteById(1);
        // User user = userService.getById(1);
        // System.out.println(user);
        //
        // for (long i = 1; i < 5; i++) {
        //     UserInfo userInfo = new UserInfo();
        //     userInfo.setUserId(i);
        //     userInfo.setUid(IdUtil.getSnowflakeNextId());
        //     mapper.insert(userInfo);
        // }

        // mapper.deleteById(1);

        // UserInfo a = UserInfo.builder().id(1L).phone("21324324423").updateTime(LocalDateTime.now()).build();
        // mapper.updateById(a);


        // UserInfo info = new UserInfo();
        // List<UserInfo> userInfos = info.selectAll();
        // userInfos.forEach(System.out::println);

        // System.out.println(service.getById(1));


        // UserInfo info = UserInfo.builder().userId(1000L).uid(IdUtil.getSnowflakeNextId()).username("高启盛").build();
        // List<UserInfo> userInfos = new ArrayList<>();
        // Collections.addAll(userInfos, UserInfo.builder().userId(101L).uid(2342342342L).username("王五").build(), UserInfo.builder().userId(102L).uid(34235L).username("赵六").build(), UserInfo.builder().userId(103L).uid(34232L).username("六七").build(), UserInfo.builder().userId(104L).uid(34325L).username("老八").build());
        // service.saveBatch(userInfos);

        // service.saveOrUpdate(info);


        // service.list().forEach(System.out::println);

        // service.page(new Page<>(1, 3)).getRecords().forEach(System.out::println);
        // System.out.println(mapper.selectById(1));
        // // List<Integer> ids = new ArrayList<>();
        // // Collections.addAll(ids, 1, 3 ,4);
        // Map<String, Object> map = new Hashtable<>();
        // map.put("gender", 0);
        // map.put()
        // List<UserInfo> userInfos = mapper.selectByMap()
        // userInfos.forEach(System.out::println);
        //


        mapper.selectList(Wrappers.<UserInfo>query().eq("id", 1).or().eq("id", 4).select("id", "uid", "username")).forEach(System.out::println);

        // UserInfo info = new UserInfo();
        // info.setId(6L);
        // info.setUid(111L);
        // info.setUsername("z行三");
        // int result = mapper.updateById(info);
        // log.debug("删除: " + result);

        // List<UserInfo> userInfos = mapper.selectList(null);
        // userInfos.forEach(System.out::println);


    }


}
