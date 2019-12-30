package com.yangqihang;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a");
        list.add(1);
        list.add(true);
        list.add("a");
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.indexOf("a"));
        System.out.println(list.lastIndexOf("a"));
        list.set(0,"yanqihang");
        System.out.println(list);
        System.out.println(list.subList(1,2));
    }
}
