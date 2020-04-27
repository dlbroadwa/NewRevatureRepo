package com.williamchung.examples.threading;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<Integer> list = new LinkedList<Integer>();

        Thread producer = new Producer(list);
        Thread consumer = new Consumer(list);

        producer.start();
        consumer.start();

//        producer.join();
//        consumer.join();
    }
}
