package com.yangqihang;

public class Account2 implements Runnable {

    private double money = 5000;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.sale();
        }
    }

    public synchronized void sale() {
        if (money > 0) {
            System.out.println(Thread.currentThread().getName() + " 准备取款");
            System.out.println(Thread.currentThread().getName() + " 完成取款");
            money -= 1000;
        } else {
            System.out.println("余额不足以支付 " + Thread.currentThread().getName() + " 的取款, 余额为" + money);
        }
    }

    public static void main(String[] args) {
        Account2 account = new Account2();
        Thread t1 = new Thread(account, "张三");
        Thread t2 = new Thread(account, "张三的妻子");
        t1.start();
        t2.start();
    }
}
