package com.yangqihang.functionref;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Test2 {

    public static String put() {
        System.out.println("put....");
        return "put";
    }

    public static void getSize(int size) {
        System.out.println(size);
    }

    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }

    public static Integer getLength(String str1, String str2) {
        return str1.length() + str2.length();
    }

    public static void main(String[] args) {
        Supplier<String> s1 = () -> Test2.put();
        System.out.println(s1.get());

        Supplier<String> s2 = Test2::put;
        System.out.println(s2.get());

        Supplier<String> s3 = Fun::hehe;
        System.out.println(s3.get());

        Consumer<Integer> c1 = Test2::getSize;
        c1.accept(1);

        Function<String, String> f1 = Test2::toUpperCase;
        System.out.println(f1.apply("aAbcc"));

        BiFunction<String, String, Integer> bf1 = (a, b) -> a.length() + b.length();
        BiFunction<String, String, Integer> bf2 = Test2::getLength;
        System.out.println(bf1.apply("abc", "abc"));
        System.out.println(bf2.apply("abc", "abc"));
    }
}

class Fun {
    public static String hehe() {
        return "hehe";
    }
}
