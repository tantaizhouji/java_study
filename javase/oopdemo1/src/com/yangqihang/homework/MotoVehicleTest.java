package com.yangqihang.homework;

public class MotoVehicleTest {

    public static void main(String[] args) {

        MotoVehicle[] moto = new MotoVehicle[5];
        moto[0] = new Car("1");
        moto[1] = new Car("1");
        moto[2] = new Car("0");
        moto[3] = new Bus(34);
        moto[4] = new Track(5);
        totalMoney(moto, 5);

        Track track = new Track(5);
        System.out.println(track.calcRent(5));
    }

    public static void totalMoney(MotoVehicle moto[], int days) {
        int totalMoney = 0;
        for (int i = 0; i < moto.length; i++) {
            totalMoney += moto[i].calcRent(5);
        }
        System.out.println("总金额是: " + totalMoney);
    }
}
