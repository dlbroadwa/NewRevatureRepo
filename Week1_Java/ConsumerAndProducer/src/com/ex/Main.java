package com.ex;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        String product = new String();

        Thread prod = new Thread(new Producer(product));
        Thread cons = new Thread(new Consumer(product));

        prod.start();
        cons.start();

        cons.join();
        prod.join();
    }
}
