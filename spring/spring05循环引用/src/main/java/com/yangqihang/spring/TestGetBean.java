package com.yangqihang.spring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestGetBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        A a = ctx.getBean("a", A.class);
        System.out.println(ToStringBuilder.reflectionToString(a));
        System.out.println(ToStringBuilder.reflectionToString(a.getB()));
        System.out.println(ToStringBuilder.reflectionToString(a.getB().getC()));

        System.out.println("================");
        A a1 = ctx.getBean("a", A.class);
        System.out.println(ToStringBuilder.reflectionToString(a1));
        System.out.println(ToStringBuilder.reflectionToString(a1.getB()));
        System.out.println(ToStringBuilder.reflectionToString(a1.getB().getC()));
    }
}
