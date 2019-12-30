package com.yangqihang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.yangqihang.mapper")
public class Springbootoa01Application {

    public static void main(String[] args) {
        SpringApplication.run(Springbootoa01Application.class, args);
    }

}
