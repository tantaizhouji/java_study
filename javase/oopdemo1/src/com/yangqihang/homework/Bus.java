package com.yangqihang.homework;

public class Bus extends MotoVehicle{

    private int seatCount;

    public Bus() {

    }

    public Bus(int seatCount) {
        this.seatCount = seatCount;
    }

    public Bus(int no, String brand, int seatCount) {
        super(no, brand);
        this.seatCount = seatCount;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    @Override
    public int calcRent(int days) {

        if(seatCount > 16) {
            return 1500*days;
        } else {
            return 800*days;
        }
    }
}
