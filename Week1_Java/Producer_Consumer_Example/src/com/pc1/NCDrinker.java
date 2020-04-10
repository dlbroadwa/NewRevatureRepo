package com.pc1;

import java.util.concurrent.BlockingQueue;

public class NCDrinker implements Runnable {
    private final BlockingQueue sharedQueue;

    public NCDrinker(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1500);
                System.out.println("Someone drank " + sharedQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
