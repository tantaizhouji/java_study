package com.yangqihang;

import java.util.LinkedList;

/*
 * LinkedList拥有更加丰富的方法实现,需要用的时候查询API即可,不需要记忆
 * */

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(123);
        linkedList.add(false);
        linkedList.add("abc");
        System.out.println(linkedList);
        linkedList.add(2, "yangqihang");
        System.out.println(linkedList);
        linkedList.addFirst(111);
        System.out.println(linkedList);
        linkedList.addLast(2222);
        System.out.println(linkedList);
        System.out.println(linkedList.element());
        linkedList.offer(333);
        System.out.println(linkedList);
    }
}
