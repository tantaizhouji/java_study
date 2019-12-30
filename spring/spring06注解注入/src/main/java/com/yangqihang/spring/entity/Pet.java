package com.yangqihang.spring.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Pet {

    @Value("胖虎")
    private String name;
    @Value("白色")
    private String color;
}
