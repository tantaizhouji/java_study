package com.yangqihang;

/*
 * Arrays提供了数据操作的工具类,包含了很多方法
 *   集合和数组之间的转换
 *       数组转成list
 * */

import java.util.Arrays;
import java.util.List;

public class ArraysDemo {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);

        //list转成数组
        Object[] objects = ints.toArray();
    }
}
