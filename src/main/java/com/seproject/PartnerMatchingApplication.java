package com.seproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.seproject.mapper")
public class PartnerMatchingApplication {
    public static void main(String[] args) {
        //启动 扫描同包和子包下的注解
        SpringApplication.run(PartnerMatchingApplication.class, args);
    }
}
