package com.yangqihang.extend;

public class Dog extends Pet {

    private String sound;

    public Dog() {
        super();
    }

    public Dog(String name, int age, String gender, String sound) {
        super(name, age, gender);
        this.sound = sound;
    }

    @Override
    public void play() {
        System.out.println("dog is play...");
    }
}
