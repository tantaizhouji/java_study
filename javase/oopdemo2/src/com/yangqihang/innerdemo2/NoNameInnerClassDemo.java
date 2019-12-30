package com.yangqihang.innerdemo2;

public class NoNameInnerClassDemo {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
