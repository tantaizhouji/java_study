package com.yangqihang.pc4;

public class Goods {
    private String brand;
    private String name;

    public Goods() {

    }

    public Goods(String brand, String name) {
        this.brand = brand;
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
