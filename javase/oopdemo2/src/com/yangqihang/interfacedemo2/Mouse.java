package com.yangqihang.interfacedemo2;

public class Mouse implements Usb {
    @Override
    public void dataTransfer() {
        System.out.println("鼠标可以移动");
    }
}
