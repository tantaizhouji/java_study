package com.yangqihang.functionref;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Test4 {

    public static void main(String[] args) {
        Consumer<Too> c1 = (Too too) -> new Too().foo();
        c1.accept(new Too());
        Consumer<Too> c2 = (Too too) -> new Too2().show("abc");
        c2.accept(new Too());
        Consumer<Too> c3 = Too::foo;
        c3.accept(new Too());

        BiConsumer<Too2, String> bc1 = (too2, str) -> new Too2().show(str);
        bc1.accept(new Too2(), "abc");
        BiConsumer<Too2, String> bc2 = Too2::show;
        bc2.accept(new Too2(), "yangqihanghang");

        BiFunction<Exec, String, Integer> bf1 = (e, s) -> new Exec().test(s);
        System.out.println(bf1.apply(new Exec(), "hehe"));
        BiFunction<Exec, String, Integer> bf2 = Exec::test;
        System.out.println(bf2.apply(new Exec(), "haha"));
    }
}

class Exec {
    public int test(String name) {
        return 1;
    }
}

class Too {
    public Integer fun(String s) {
        return 1;
    }

    public void foo() {
        System.out.println("foo...");
    }
}

class Too2 {
    public Integer fun(String s) {
        return 1;
    }

    public void foo() {
        System.out.println("foo---too2");
    }

    public void show(String str) {
        System.out.println("show---too2" + str);
    }
}
