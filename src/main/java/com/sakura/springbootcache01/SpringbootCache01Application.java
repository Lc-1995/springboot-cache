package com.sakura.springbootcache01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching // 开启注解缓存
@SpringBootApplication
@MapperScan("com.sakura.springbootcache01.entity")
public class SpringbootCache01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCache01Application.class, args);
    }
}
