package com.company;

public class SteelRollingMill implements Runnable {
    private Storage storage;

    public SteelRollingMill(Storage storage) {
        this.storage = storage;
    }


    @Override
    public void run() {
        // every 5 seconds consume 5 rolls
        // if empty wait an additional 10 seconds
        while(true) {

            try {
                Thread.sleep(5000);
                storage.binarySemaphore.acquire();
                if (!storage.isFull()) {
                    storage.addRolls(5);
                    storage.binarySemaphore.release();
                } else {
                    storage.binarySemaphore.release();
                    Thread.sleep(15000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
