package com.pc1;

import java.util.concurrent.BlockingQueue;

public class NCMaker implements Runnable {
    /*@Override
    public void run() {
        int value = 0;
        while (true) {
            synchronized (this) {
                while(list.size() == value)
            }
        }
    }*/
    private final BlockingQueue sharedQueue;

    public NCMaker(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i <10; i++) {
            try {
                Thread.sleep(500);
                System.out.println("Nuka Cola Maker produced " + i + " Nuka Cola");
                sharedQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
