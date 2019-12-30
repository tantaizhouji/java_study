package com.yangqihang.lambda;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaTest {
    public static void main(String[] args) throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("running1.....");
            }
        };
        runnable.run();

        Runnable runnable1 = () -> System.out.println("running2.....");
        runnable1.run();

        Callable<String> c1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "mashibing";
            }
        };
        System.out.println(c1.call());

        Callable<String> c2 = () -> {
            return "mashibing2";
        };
        System.out.println(c2.call());

        Callable<String> c3 = () -> "mashibing3";
        System.out.println(c3.call());

        StudentDao sd1 = new StudentDao() {
            @Override
            public void insert(Student student) {
                System.out.println("插入学生1");
            }
        };

        StudentDao sd2 = (student) -> System.out.println("student2: " + student);

        StudentDao sd3 = (Student student) -> System.out.println("student3: " + student);

        sd1.insert(new Student());
        sd2.insert(new Student());
        sd3.insert(new Student());

        TeacherDao td1 = new TeacherDao() {
            @Override
            public int get(Teacher teacher) {
                return 1;
            }
        };

        TeacherDao td2 = (teacher) -> {
            return 2;
        };

        TeacherDao td3 = teacher -> 3;

        System.out.println(td1.get(new Teacher()));
        System.out.println(td2.get(new Teacher()));
        System.out.println(td3.get(new Teacher()));

        /*
         * 在Java中提供了一系列的函数式接口,用来接受后续传入的逻辑,但是对输入和输出有要求
         * */
        Function<String, Integer> f1 = (str) -> str.length();
        System.out.println(f1.apply("abcdefg"));

        Supplier<String> s1 = () -> "mashibing";
        System.out.println(s1.get());

        Consumer<String> cs1 = (str) -> System.out.println(str);
        cs1.accept("你好");

        Runnable runnable2 = () -> get();

        Runnable runnable3 = () -> exec();


        runnable2.run();
        runnable3.run();

        LambdaInterface lambdaInterface = () -> get();
        LambdaInterface lambdaInterface1 = () -> find();

        System.out.println(lambdaInterface.get());
        System.out.println(lambdaInterface1.get());

        BiFunction<String, String, Integer> biFunction = (a, b) -> a.length() + b.length();
        System.out.println(biFunction.apply("连老师", "好帅"));
    }

    static int get() {
        return 1;
    }

    static String find() {
        return "find";
    }

    static void exec() {
        find();
    }

}
