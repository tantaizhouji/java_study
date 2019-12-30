package com.yangqihang.spring.controller;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import com.yangqihang.spring.entity.User;
import com.yangqihang.spring.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 负责逻辑跳转
 * 在Web环境下，由Controller层先接入
 */
@Component("MainController")
public class MainController {


    @Autowired
    private MainService srv;

    public void list()  {
        String loginName = "zhangfg";
        String password = "123456";
        String str = srv.login(loginName, password);

        System.out.println(str);
    }
}
