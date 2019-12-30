package com.yangqihang.innerdemo2;

public class Outer {

    private String name = "zhangsan";

    class InnerClass {
        private String name = "lisi";

        public void show() {
            System.out.println(name);
            System.out.println(this.name);
            System.out.println(Outer.this.name);
        }
    }

    public static void main(String[] args) {
        Outer.InnerClass innerClass = new Outer().new InnerClass();
        innerClass.show();
    }
}
