package com.company;

public class Main {

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
	    Thread producer = new Thread(new Producer(buffer));
	    Thread consumer = new Thread(new Consumer(buffer));

	    producer.start();
	    consumer.start();

        System.out.println("Main ending");
    }
}
