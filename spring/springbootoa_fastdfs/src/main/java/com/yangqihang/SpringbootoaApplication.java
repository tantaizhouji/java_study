package com.yangqihang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yangqihang.mapper")
public class SpringbootoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootoaApplication.class, args);
    }

}
