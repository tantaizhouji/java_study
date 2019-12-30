package com.yangqihang.innerdemo;

public class InnerClassDemo {
    private int id;
    private String name;

    public InnerClassDemo() {

    }

    public InnerClassDemo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("show");
        InnerClass innerClass = new InnerClass();
        innerClass.test();
    }

    class InnerClass {
        private int age;

        public void test() {
            System.out.println("test");
            System.out.println(id);
            System.out.println(name);
        }
    }
}
