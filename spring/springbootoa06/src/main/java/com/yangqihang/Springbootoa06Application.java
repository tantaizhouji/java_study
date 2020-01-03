package com.yangqihang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yangqihang.mapper")
public class Springbootoa06Application {

    public static void main(String[] args) {
        SpringApplication.run(Springbootoa06Application.class, args);
    }

}
