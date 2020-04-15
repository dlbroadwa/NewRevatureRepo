package com.ex;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Thread prod1 = new Producer("Producer 1");
        Thread cons1 = new Thread(new Consumer("Consumer 1"));

        prod1.start();
        cons1.start();

        System.out.println("The program has finished execution");

    }
}
