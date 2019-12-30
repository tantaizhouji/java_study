package com.yangqihang.functionref;

import com.yangqihang.why.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Test3 {

    public String put() {
        return "put...";
    }

    public void getSize(int size) {
        System.out.println("size: " + size);
    }

    public String toUpperCase(String str) {
        return str.toUpperCase();
    }

    public static void main(String[] args) {

        System.out.println(new Test3().put());
        Supplier<String> s1 = () -> new Test3().put();
        Supplier<String> s2 = new Test3()::put;
        System.out.println(s1.get());
        System.out.println(s2.get());

        //唯一的创建一个Test3对象
        Test3 test3 = new Test3();

        Consumer<Integer> c1 = (size) -> new Test3().getSize(size);
        Consumer<Integer> c2 = new Test3()::getSize;
        Consumer<Integer> c3 = test3::getSize;
        c1.accept(123);
        c2.accept(123);
        c3.accept(123);

        Function<String, String> f1 = (str) -> new Test3().toUpperCase(str);
        Function<String, String> f2 = new Test3()::toUpperCase;
        Function<String, String> f3 = test3::toUpperCase;
        System.out.println(f1.apply("abc"));
        System.out.println(f2.apply("abc"));
        System.out.println(f3.apply("abc"));

    }
}
