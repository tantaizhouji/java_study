package com.yangqihang.spring;

import com.yangqihang.spring.controller.MainController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        MainController mainController = ctx.getBean("MainController", MainController.class);
        mainController.list();
    }
}
