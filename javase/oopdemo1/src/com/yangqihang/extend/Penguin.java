package com.yangqihang.extend;

public class Penguin extends Pet {

    private String color;

    public Penguin() {
        super();
    }

    public Penguin(String name, int age, String gender, String color) {
        super(name, age, gender);
        this.color = color;
    }
}
