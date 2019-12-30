package com.yangqihang.spring;

public class CarFactory {

    public Car getCar(String name) throws Exception {
        if (name.equals("bmw")) {
            return new BMW();
        } else if (name.equals("audi")) {
            return new Audi();
        }

        throw new Exception("没有这种车");
    }
}
