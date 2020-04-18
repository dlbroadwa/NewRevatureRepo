package com.williamchung.examples.threading;

import java.util.LinkedList;

public class Consumer extends Thread {
    LinkedList<Integer> list;

    public Consumer(LinkedList<Integer> list) {
        this.list = list;
    }

    public void run(){
        try{
            this.consume(list);
        } catch (InterruptedException exception) {
            System.out.println(exception);
        }
    }

    public void consume(LinkedList<Integer> list) throws InterruptedException {
        while (true) {
            synchronized (list) {
                while (list.isEmpty()) {
                    list.wait();
                }
                int job = list.removeFirst();
                System.out.println("consumed job #" + job);
                list.notifyAll();
                Thread.sleep(500);
            }
        }
    }
}
