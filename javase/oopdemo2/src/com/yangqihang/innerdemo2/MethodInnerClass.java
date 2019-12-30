package com.yangqihang.innerdemo2;

public class MethodInnerClass {

    public void show() {
        System.out.println("show");

        class InnerClass {
            private String name;

            public void test(int a) {
                System.out.println("test");
                System.out.println(a);
            }
        }

        new InnerClass().test(12);
    }

    public static void main(String[] args) {
        MethodInnerClass methodInnerClass = new MethodInnerClass();
        methodInnerClass.show();
    }
}
