package com.yangqihang;

/*
 * 介绍线程类API方法
 * */

public class ThreadApiDemo {
    public static void main(String[] args) {
        //获取当前线程对象
        Thread thread = Thread.currentThread();
        //获取当前线程名称
        System.out.println(thread.getName());
        //获取线程的ID
        System.out.println(thread.getId());
        //获取线程的优先级,在一般的系统中范围是0~10的值,如果没有经过设置的话,就是默认值5,有些系统是0~100
        System.out.println(thread.getPriority());
        //设置线程池的优先级
        //优先级越高一定就越先执行吗?不一定,只是优先执行的概率比较大而已
        thread.setPriority(10);
        System.out.println(thread.getPriority());
    }
}
