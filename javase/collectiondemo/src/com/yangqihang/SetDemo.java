package com.yangqihang;

import java.util.*;

/*
 * 1.Set中存放的是唯一,无序的数据
 * 2.Set不可以通过下标获取对应位置的元素的值,因为无序的特点
 * 3.TreeSet底层的实现是TreeMap,利用红黑树来进行实现
 * 4.设置元素的时候,如果是自定义对象,会查找对象中的equals和hashCode的方法,如果没有,比较的是地址
 * 5.树中的元素是要默认进行排序操作的,如果是基本数据类型,自动比较,如果是引用类型的话,需要自定义比较器
 *      比较器分类:
 *          内部比较器
 *              定义在元素的类中,通过实现Comparable接口来实现
 *          外部比较器
 *              定义在当前类中,通过实现Comparable接口来实现,但是要将该比较器传递到集合中
 *          注意:
 *              外部比较器可以定义成一个工具类,此时所有需要比较的规则如果一致的话,可以复用
 *              而内部比较器只有在存储当前对象的时候才可以使用
 *              如果两者同时存在,使用外部比较器
 *              当使用比较器的时候,不会调用equals方法
 * */

public class SetDemo implements Comparator<Person> {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add("abc");
        set.add(2);
        set.add(true);
        set.add(2);
        System.out.println(set);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("---------------------------------");
        //将while循环改成for循环,推荐使用
        for (Iterator iterator1 = set.iterator(); iterator1.hasNext(); ) {
            System.out.println(iterator1.next());
        }

        TreeSet treeSet = new TreeSet();
        treeSet.add(51);
        treeSet.add(34);
        treeSet.add(65);
        treeSet.add(1);
        System.out.println(treeSet);
        //在集合里取给定数值大于等于的值
        System.out.println(treeSet.ceiling(3));
        treeSet.clear();
        System.out.println("----------------------------------");

        HashSet hashSet = new HashSet();
        hashSet.add(new Person("大狗", 5));
        hashSet.add(new Person("大狗", 5));
        hashSet.add(new Person("二狗", 3));
        hashSet.add(new Person("狗蛋", 7));
        System.out.println(hashSet);
        System.out.println("----------------------------------");


        TreeSet treeSet1 = new TreeSet(new SetDemo());
        treeSet1.add(new Person("lisi", 5));
        treeSet1.add(new Person("lisi", 5));
        treeSet1.add(new Person("zhangsan", 3));
        treeSet1.add(new Person("wangwu", 7));
        System.out.println(treeSet1);
    }

    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getAge() > o2.getAge()) {
            return 1;
        } else if (o1.getAge() < o2.getAge()) {
            return -1;
        } else {
            return 0;
        }
    }
}
