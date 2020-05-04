package com.revature.day4;

import java.util.LinkedList;
import java.util.Random;

public class Producer implements Runnable {
    private LinkedList<Integer> list ;
    private int maxSize;

    public Producer ( LinkedList<Integer> linkedList, int size){
        list = linkedList;
        maxSize = size;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++) {
            Random rand = new Random ();
            int num = rand.nextInt(10);
            System.out.println("Insert: " + num);
            synchronized (list){
                while (list.size() == maxSize) {
                    System.out.println("List full.  Please wait");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
             }

             synchronized (list){

                list.add(new Integer(num));
                list.notifyAll();
             }
        }
    }
}
