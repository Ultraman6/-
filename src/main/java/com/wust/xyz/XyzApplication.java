package com.wust.xyz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wust.xyz.mapper")
public class XyzApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyzApplication.class, args);
    }

}
