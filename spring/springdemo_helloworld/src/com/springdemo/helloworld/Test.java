package com.springdemo.helloworld;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Person person = ctx.getBean("person", Person.class);
        Person person1 = ctx.getBean("person1", Person.class);
        person.setName("HelloSpring");
        System.out.println(person.getName());
        System.out.println(person1.getName());
        System.out.println(person);
        System.out.println(person1);
    }
}
