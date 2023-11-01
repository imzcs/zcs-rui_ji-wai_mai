package com.zcs.ruijiwaimai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zcs.ruijiwaimai.mapper")
public class RuiJiWaiMaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuiJiWaiMaiApplication.class, args);
    }

}
