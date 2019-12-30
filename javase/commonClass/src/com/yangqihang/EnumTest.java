package com.yangqihang;

public class EnumTest {
    public static void main(String[] args) {
        Gender gender = Gender.man;
        Gender gender1 = Gender.woman;

        EventEnum ee = EventEnum.EVENT;
        ee.show();
        String name = EventEnum.LAUNCH.name();
        System.out.println(name);
    }
}
