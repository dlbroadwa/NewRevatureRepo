package com.ex;


public class Main {

    public static void main(String[] args) {
        String product = new String();

        Thread prod = new Thread(new Producer(product));
        Thread cons = new Thread(new Consumer(product));

        prod.start();
        try {
            prod.join();
            cons.join();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        cons.start();
    }
}
