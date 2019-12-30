package com.yangqihang.callable;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName() + "is running";
    }
}
