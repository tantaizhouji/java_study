package com.yangqihang.extend;

public class PetTest {

    public static void main(String[] args) {

        Dog dog = new Dog("大黑",21,"男","汪");
        dog.setName("大狗");
        System.out.println(dog.getName());
        Pet pet = new Pet();
        System.out.println(pet);
    }
}
