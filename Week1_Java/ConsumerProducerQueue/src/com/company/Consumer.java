package com.company;

import java.util.Queue;

public class Consumer implements Runnable {

    private final Queue sharedQueue;

    public Consumer(Queue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                System.out.println("Consumed: " + sharedQueue.poll());
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}
