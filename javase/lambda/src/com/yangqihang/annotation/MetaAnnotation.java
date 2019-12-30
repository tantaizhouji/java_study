package com.yangqihang.annotation;

import java.lang.annotation.*;

public class MetaAnnotation {

//    @MyAnootation(name = "hehe", age = 13, id = 1, likes = {"A", "b", "dssad"})
    @MyAnootation
    public void test() {

    }
}

//Target用来声明当前自定义的注解适合适用于什么地方,类,方法,变量,包...
@Target({ElementType.METHOD, ElementType.TYPE})
//Retention用来表示当前注解适用于什么环境,是源码级别还是类级别还是运行时环境,一般都是运行时环境
@Retention(RetentionPolicy.RUNTIME)
//Documented表示该注解是否显示在javadoc中
@Documented
//Inherited表示当前注解是否能够被继承
@Inherited
@interface MyAnootation {

    //定义的方式看起来像是方法,但是实际上是在使用注解的时候填写的参数的名称,默认的名称是value
    //自定义注解中填写的所有方法都需要使用注解的时候,添加值很麻烦，因此包含默认值
    String name() default "zhangsan";

    int age() default 12;

    int id() default 1;

    String[] likes() default {"a", "b"};
}
