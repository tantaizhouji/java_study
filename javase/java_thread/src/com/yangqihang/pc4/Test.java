package com.yangqihang.pc4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {
    public static void main(String[] args) {
        BlockingQueue<Goods> blockingQueue = new ArrayBlockingQueue<Goods>(5);
        ProducerQueue producerQueue = new ProducerQueue(blockingQueue);
        ConsumerQueue consumerQueue = new ConsumerQueue(blockingQueue);
        new Thread(producerQueue).start();
        new Thread(consumerQueue).start();
    }
}
