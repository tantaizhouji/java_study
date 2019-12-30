package com.yangqihang;

/*
 * 买水,3个瓶子可以换1瓶水,问27瓶水最少需要买几瓶?
 * */

public class BuyWater {

    public static void main(String[] args) {
        sum(27);
    }

    public static void sum(int x) {
        int count = 0;
        int sum = 0;
        for (int i = 1; i <= x; i++) {
            count++;
            sum++;
            if (count == 3) {
                sum++;
                count = 1;
            }
            System.out.println("买的瓶数:i=" + i);
            System.out.println("空瓶子数=" + count);
            System.out.println("总瓶数=" + sum);
        }
    }
}
