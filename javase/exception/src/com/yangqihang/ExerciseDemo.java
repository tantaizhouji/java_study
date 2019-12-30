package com.yangqihang;

import java.util.Scanner;

public class ExerciseDemo {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("请输入课程编号:");
        try {
            int id = in.nextInt();
            if (id == 1) {
                System.out.println("Java");
            } else if (id == 2) {
                System.out.println("C++");
            } else {
                System.out.println("python");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("欢迎选课");
        }
    }
}
