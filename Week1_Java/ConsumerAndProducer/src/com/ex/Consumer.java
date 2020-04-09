package com.ex;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    String product;
    private BlockingQueue buffer;

    public Consumer(BlockingQueue buffer) {
        this.buffer = buffer;
    }


    @Override
    public void run() {
        try {
            product = (String) buffer.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The consumer purchased your product: " + product);
    }
}