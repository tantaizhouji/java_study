package com.yangqihang;

/*
 *将自然数按从小到大的顺序无间隔地排成大数：12345678910111213......问：从左起的100位的数字是几?
 * */

public class NaturalNumbers {
    public static void main(String[] args) {
        /*将所有数字添加在字符串中,判断字符串长度是否大于100,大于的时候转化成字符数组并取出第100个数
        * */

        //定义第1个数1
        int i = 1;
        //初始化字符串的长度为0
        int length = 0;
        //初始化字符串为空
        String str = "";
        //判断字符串长度是否达到100,没达到继续往里面添加
        while (length <= 100) {
            //将数字转换成字符串并添加到后面
            str = str + String.valueOf(i);
//            System.out.println(str);
            //更新字符串长度
            length = str.length();
//            System.out.println(length);
            //数字递增
            i++;
        }
        //输出第100个数
        System.out.println(str.toCharArray()[99]);
    }
}
