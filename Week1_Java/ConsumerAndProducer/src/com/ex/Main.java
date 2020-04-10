package com.ex;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String product = new String();

        BlockingQueue buffer = new LinkedBlockingQueue();
        BlockingQueue num = new LinkedBlockingQueue();

        Thread prod = new Thread(new Producer(buffer,num));
        Thread cons = new Thread(new Consumer(buffer,num));

        prod.start();
        cons.start();

    }
}
