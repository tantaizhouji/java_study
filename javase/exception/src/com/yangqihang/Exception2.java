package com.yangqihang;

/*
 * 当异常情况出现的时候,可以用try...catch...finally的方式对异常进行处理,除此之外,可以将异常向外抛出,由外部进行处理
 * 1.在方法调用的过程中,可以存着N多个方法之间的调用,此时假如每个方法都包含了异常情况,那么就需要在每个方法中进行tyr...catch操作
 *   另外一种比较简单的方式,就是在方法的最外层调用处理一次即可,使用throws的方法，对所有执行过程中的所有方法出现的异常进行统一姬祭仲处理
 * 2.如何判断是使用throws还是try...catch
 *      最稳妥的方式是在每个方法中都进行异常的处理
 *      偷懒的方式是判断在整个调用的过程中,外层的调用方法是否有对异常的处理,如果有,直接使用throws，如果没有,那么就使用try...catch
 * throw:抛出异常
 * */

public class Exception2 {

    public static void main(String[] args) {
        try {
//            test();
            show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("hehe");
        }
    }

    public static void show() throws Exception{
        String gender = "123";

        if(gender.equals("man")) {
            System.out.println("man");
        } else if(gender.equals("woman")) {
            System.out.println("woman");
        } else {
//            throw new Exception("性别错误");
            throw new GenderException("gender is error");
        }
    }

    public static void test() throws Exception {
        test1();
        System.out.println(11 / 0);
    }

    public static void test1() throws Exception {
        test2();
        System.out.println(111 / 0);
    }

    public static void test2() throws Exception {
        test3();
        System.out.println(1111 / 0);
    }

    public static void test3() throws Exception {
        System.out.println(11111 / 0);
    }
}
