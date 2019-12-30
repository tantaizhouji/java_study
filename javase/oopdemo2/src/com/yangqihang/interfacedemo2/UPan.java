package com.yangqihang.interfacedemo2;

public class UPan implements Usb {
    @Override
    public void dataTransfer() {
        System.out.println("U盘进行数据传输");
    }
}
