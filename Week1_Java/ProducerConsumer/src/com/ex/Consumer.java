package com.ex;

import java.util.LinkedList;

public class Consumer implements Runnable {
    private final LinkedList<Integer> list;
    public Consumer(LinkedList<Integer> sList) {
        this.list = sList;
    }

    @Override
    public void run() {
        while (true) {
            try {
                take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void take() throws InterruptedException {
        synchronized (list) {
            while (list.isEmpty()) {
                System.out.println("Customer needs their order");
                list.wait();
            }
            int taken = list.remove(0);
            System.out.println("Customer took order " + taken);
            list.notifyAll();
            Thread.sleep(750);
        }
    }

}