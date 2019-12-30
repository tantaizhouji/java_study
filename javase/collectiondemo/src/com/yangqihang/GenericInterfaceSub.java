package com.yangqihang;

public class GenericInterfaceSub implements GenericInterface<String> {
    @Override
    public String test() {
        return "test";
    }

    @Override
    public void test2(String s) {
        System.out.println(s);
    }
}
