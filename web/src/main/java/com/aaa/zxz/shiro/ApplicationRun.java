package com.aaa.zxz.shiro;
/*
 * @Author zxz
 * @Description //TODO
 * @Date 9:42 2019/8/19
 * @Param
 * @return
 **/

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aaa.zxz.shiro.mapper")
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }
}
