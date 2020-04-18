package com.williamchung.examples.threading;

import java.util.LinkedList;

public class Producer extends Thread {
    LinkedList<Integer> list;

    int listCapacity = 5;
    int jobCounter = 0;

    public Producer (LinkedList<Integer> list) {
        this.list = list;
    }

    public void run(){
        try{
            this.produce(list);
        } catch (InterruptedException exception) {
            System.out.println(exception);
        }
    }

    public void produce(LinkedList<Integer> list) throws InterruptedException {
        while(true){
            synchronized (list) {
                while(list.size() >= listCapacity){
                    list.wait();
                }
                System.out.println("produced job #" + jobCounter);
                list.add(jobCounter);
                jobCounter++;
                list.notifyAll();
                Thread.sleep(500);
            }
        }
    }
}
