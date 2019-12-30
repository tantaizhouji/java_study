package com.yangqihang.exercise;

public class Test1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "-----" + i);
            try {
                Thread.sleep(1001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Thread thread = new Thread(test1);
        thread.start();
        for (int i = 10; i > 0; i--) {
            System.out.println(Thread.currentThread().getName() + "=====" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
