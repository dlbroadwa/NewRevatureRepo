package com.revature.day4;

import java.util.LinkedList;
import java.util.Random;

public class Consumer implements Runnable {
    private LinkedList<Integer> list ;
    private int maxSize;

    public Consumer (LinkedList<Integer> linkedList, int size){
        list = linkedList;
        maxSize = size;
    }

    @Override
    public void run() {
        while (true){
           // System.out.print("Remove: " + list.get(0)):
            while (list.isEmpty()){
                synchronized (list) {
                    System.out.println("List is empty, waiting for additional nodes");

                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }//end inner while loop

            synchronized (list){
                list.notifyAll();
                list.remove(0);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
