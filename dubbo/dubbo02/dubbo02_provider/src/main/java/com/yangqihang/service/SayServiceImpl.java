package com.yangqihang.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0", timeout = 10000, interfaceClass = ISayService.class)
@Component
public class SayServiceImpl implements ISayService {
    @Override
    public String say(String name) {
        String str = "Hello! " + name;

        System.out.println(str);
        return str;
    }
}
