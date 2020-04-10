package com.ex;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    String product;
    int i;
    private BlockingQueue buffer;
    private BlockingQueue num;

    public Consumer(BlockingQueue buffer, BlockingQueue num) {
        this.buffer = buffer;
        this.num = num;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            while(i<4){
                product = (String) buffer.take();
                i = (int) num.take();
                System.out.println("The consumer purchased " + i + " " + product);
            }
        } catch (InterruptedException ex){
                ex.printStackTrace();
            }


    }
}