package com.ex;

public class Consumer implements Runnable {

    String product;

    public Consumer(String product){
        this.product=product;
    }


    @Override
    public void run() {
        System.out.println("The consumer purchased your product: " + this.product);
    }


}
