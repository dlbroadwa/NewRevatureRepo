package com.company;

import java.util.Queue;

public class Producer implements Runnable {

    private final Queue sharedQueue;

    public Producer(Queue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++)
        {
            try {
                System.out.println("Produced: " + i);
                sharedQueue.add(i);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
        }

    }
}
