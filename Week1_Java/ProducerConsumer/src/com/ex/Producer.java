package com.ex;

import java.util.LinkedList;

public class Producer implements Runnable {
    private final LinkedList<Integer> list;
    private final int cap;

    public Producer (LinkedList<Integer> sList, int capacity){
        this.list=sList;
        this.cap = capacity;
    }
    @Override
    public void run() {
        int starter = 101;
        while(true){
            try {
                make(starter++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void make(int value) throws InterruptedException{
            synchronized (list){
                while(list.size() == cap) {
                    System.out.println("full");
                    list.wait();
                }
                System.out.println("Employee made order " + value);
                list.add(value++);
                list.notifyAll();
                Thread.sleep(1000);
            }
        }

}
