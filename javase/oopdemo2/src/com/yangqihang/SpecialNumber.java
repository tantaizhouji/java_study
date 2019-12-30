package com.yangqihang;

/*
 * “特殊数字”是指只能被分解为2，3，7的乘积的数字。“特殊数字”序列为1, 2, 3, 4, 6, 7, 8, 9, 12, 14, ...展示了前10个“特殊数字”。
 * 按照惯例，1也是“特殊数字”。给定整数n，写一个程序，找到序列中，第n个“特殊数字”。如：n=9，找到“特殊数字”序列中第9位数字，也就是输出12。
 * */

public class SpecialNumber {

    public static void main(String[] args) {
        out(9);
    }

    public static void out(int n) {
        int i = 1;
        int j = 0;
        for (; j <= n; i++) {
            if (i == 1 || i % 2 == 0 || i % 3 == 0 || i % 7 == 0) {
                j++;
            }
        }
        System.out.println(i - 1);
    }
}

