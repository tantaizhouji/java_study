package com.yangqihang;

public class JoinTest {
    public static void main(String[] args) {
        MyRun run = new MyRun();
        Thread thread = new Thread(run);
        thread.start();

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
            if (i == 3) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
