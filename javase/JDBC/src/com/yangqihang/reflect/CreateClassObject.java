package com.yangqihang.reflect;


import com.yangqihang.entity.Emp;

public class CreateClassObject {
    public static void main(String[] args) throws ClassNotFoundException {
        //1.通过Class.forName()来获取对象
        Class clazz = Class.forName("com.yangqihang.entity.Emp");
        //2.通过类名.class来获取
//        Class<Emp> clazz = Emp.class;
        //3.通过对象的getClass()来获取
//        Class clazz = new Emp().getClass();
        System.out.println(clazz);
        System.out.println(clazz.getPackage());
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getCanonicalName());

        //4.如果是一个基本数据类型,那么可以通过TYPE的方式来获取Class对象
        Class<Integer> type = Integer.TYPE;
        System.out.println(type.getName());
        System.out.println(type.getCanonicalName());
    }
}
