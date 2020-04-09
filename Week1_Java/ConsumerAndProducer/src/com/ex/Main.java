package com.ex;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String product = new String();

        BlockingQueue buffer = new LinkedBlockingQueue();

        Thread prod = new Thread(new Producer(buffer));
        Thread cons = new Thread(new Consumer(buffer));

        prod.start();
        cons.start();

    }
}
