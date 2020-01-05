package com.yangqihang.springbootmvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yangqihang.springbootmvc.mapper")
public class SpringbootmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmvcApplication.class, args);
    }

}
