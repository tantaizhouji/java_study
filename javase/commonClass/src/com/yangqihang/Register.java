package com.yangqihang;

import java.util.Scanner;

public class Register {

    public static void main(String[] args) {

        System.out.println("***欢迎进入注册系统***");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        String name = null;
        String password = null;
        String password1 = null;

        do {
            System.out.print("请输入用户名: ");
            name = sc.nextLine();
            System.out.print("请输入密码: ");
            password = sc.nextLine();
            System.out.print("请再次输入密码: ");
            password1 = sc.nextLine();
        } while (!verify(name, password, password1));

        System.out.println("注册成功!请牢记用户名和密码");
    }

    public static boolean verify(String name, String password, String password1) {

        if (name.length() < 3 || password.length() < 6) {
            System.out.println("用户名长度不能小于3,密码长度不能小于6！");
            return false;
        } else if (!password.equals(password1)) {
            System.out.println("两次输入的密码不相同!");
            return false;
        } else {
            return true;
        }

    }
}
