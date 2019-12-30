package com.yangqihang.stream;

public class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public static Person build(String name) {
        Person p = new Person();
        p.setName(name);
        return p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    @Deprecated
    public void show(){
        System.out.println("show");
    }

    public static void main(String[] args) {

        Person p = new Person();
    }
}
