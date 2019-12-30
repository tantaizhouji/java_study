package com.yangqihang;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {

        List list = new ArrayList();
        Dog d1 = new Dog("大狗","yellow");
        Dog d2 = new Dog("二狗","red");
        Dog d3 = new Dog("狗蛋","black");
        list.add(d1);
        list.add(d2);
        list.add(d3);
        System.out.println(list);
        System.out.println(list.size());
        list.remove(d1);
        System.out.println(list);
        Dog d4 = new Dog("二狗","red");
        System.out.println(list.contains(d4));
    }
}
