package com.yangqihang.homework;

public class Track extends MotoVehicle {

    private int weight;

    public Track() {

    }

    public Track(int weight) {
        this.weight = weight;
    }

    public Track(int no, String brand, int weight) {
        super(no, brand);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int calcRent(int days) {
        return 50 * days * weight;
    }
}
