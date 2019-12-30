package com.yangqihang.homework;

public class Car extends MotoVehicle{

    private String type;

    public Car() {
        super();
    }

    public Car(String type) {
        this.type = type;
    }

    public Car(int no, String brand, String type) {
        super(no, brand);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int calcRent(int days) {

        if(type.equals("0")) {
            return 600*days;
        } else if(type.equals("1")) {
            return 500*days;
        } else if(type.equals("2")) {
            return 300*days;
        } else {
            System.out.println("型号错误");
            return 0;
        }
    }
}
