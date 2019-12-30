package com.yangqihang;

import java.util.ArrayList;
import java.util.Collection;

/*
* Java集合框架:
*   Collection:存放的是单一值
*       特点:
*           1.可以存放不同类型的数据,而数组只能存放固定类型的数据
*           2.当使用ArrayList子类实现的时候,初始化的长度是10,当长度不够的时候会自动进行扩容操作
*       api方法:
*       增加数据的方法
*           add:要求必须传入的参数是Object对象,因此写入基本数据类型的时候,包含了自动拆箱和自动装箱的过程
*           addAll:添加另一个集合的元素到此集合中
*       删除数据的方法
*           clear:只是清空集合中的元素,但是此集合对象并没有被回收
*           remove:删除指定元素
*           removeAll:删除集合元素
*       查询数据的方法
*           contains:判断集合中是否包含指定的元素值
*           containsAll:取此集合中是否包含另一个集合
*           isEmpty:判断集合是否等于空
*           retainAll:保留该集合与指定集合的交集(共同元素)
*           size:返回当前集合的大小
*       集合转数组的操作
*           toArray:将集合转换成数组
* */

public class CollectionDemo {
    public static void main(String[] args) {
        //创建集合collection
        Collection collection = new ArrayList();
        //向集合内部添加元素
        collection.add(1);
        collection.add("abc");
        collection.add(true);
        collection.add(1.34);
        collection.add("1");
        System.out.println(collection);
        //ArrayList中的方法,在指定下标位置添加元素
        ((ArrayList) collection).add(0,"yangqihang");
        System.out.println(collection);
        //创建集合collection1, collection2
        Collection collection1 = new ArrayList();
        Collection collection2 = new ArrayList();
        collection1.add("a");
        collection1.add("b");
        collection1.add("c");
        collection2.add("d");
        //往集合内部添加另一个集合内的所有元素
        collection.addAll(collection1);
        System.out.println(collection);
        //判断集合内部是否包含该元素
        System.out.println(collection.contains("a"));
        //判断集合内部是否包含另一个集合
        System.out.println(collection.containsAll(collection1));
        System.out.println("-----------------------------");
        //删除指定的元素，返回值为boolean
        System.out.println(collection.remove("1"));
        collection.remove("a");
        System.out.println(collection);
        System.out.println("-----------------------------");
        System.out.println(collection1);
        System.out.println(collection2);
        //取集合中与指定集合共有的元素
        collection1.retainAll(collection2);
        System.out.println(collection1);
        System.out.println("-----------------------------");
        //删除集合内另一个集合的所有元素
        collection.removeAll(collection1);
        System.out.println(collection);
        //清空集合，集合变为空集合
        collection.clear();
        System.out.println(collection);
        //判断集合是否为空
        System.out.println(collection.isEmpty());
    }
}
