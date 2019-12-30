package com.yangqihang;

/*
 * 包装类和基本数据类型
 *   包装类是将基本数据类型封装成一个类,包含属性和方法
 *   使用:
 *       在使用过程中,会涉及到自动装箱和自动拆箱
 *       装箱:将基本数据类型转换成包装类
 *       拆箱:将包装类转换成基本数据类型
 * */

public class IntegerDemo {

    public static void main(String[] args) {
        int a = 10;
        Integer i = new Integer(10);
        //通过方法进行类型的转换
        //基本类型转换成包装类
        Integer i2 = Integer.valueOf(a);
        //包装类转换成基本类型
        int i3 = i.intValue();
        System.out.println(a == i);
        Float f = new Float(3.14);
        Double d = new Double(3.14);
        System.out.println("-----------------");

        Integer s1 = 100;
        Integer s2 = 100;
        Integer s3 = 200;
        Integer s4 = 200;
        //当数值在-128~127中，不会new新对象，故是true
        System.out.println(s1 == s2);
        //数值不在范围内,会new新对象,false
        System.out.println(s3 == s4);
        System.out.println("-----------------");

        Double d1 = 1.0;
        Double d2 = 1.0;
        Double d3 = 2.0;
        Double d4 = 2.0;
        System.out.println(d1 == d2);
        System.out.println(d3 == d4);
        System.out.println("-----------------");

        Integer n = 10;
        int m = n;
        System.out.println(n == m);
    }
}
