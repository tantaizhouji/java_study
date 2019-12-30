package com.yangqihang.spring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Person person = ctx.getBean("person",Person.class);
        System.out.println(ToStringBuilder.reflectionToString(person));
    }
}
