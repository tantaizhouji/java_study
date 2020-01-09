package com.yangqihang;

public class SayServiceImpl implements ISayService {
    @Override
    public String say(String name) {
        System.out.println("hello! " + name);
        return "hello! " + name;
    }
}
