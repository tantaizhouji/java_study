package com.yangqihang.controller;

import com.yangqihang.service.ISayService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @Reference(version = "1.0.0")
    ISayService saySrv;

    @RequestMapping("/say")
    public String say() {
        return saySrv.say("马老师");
    }
}
