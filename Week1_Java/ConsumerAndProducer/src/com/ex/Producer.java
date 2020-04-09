package com.ex;

import java.util.Scanner;

public class Producer implements Runnable {

    Scanner s = new Scanner(System.in);
    String product = new String();

    public Producer(String product) {
        this.product = product;
    }


    public String setProduct() {
        synchronized (this) {
            return this.product;
        }
    }

    public String getProduct() {
        synchronized (this) {
            return this.product;
        }
    }

    @Override
    public void run() {
        System.out.println("What product are you selling?");
        this.product = s.nextLine();
    }
}