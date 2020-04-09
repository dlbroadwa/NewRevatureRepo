package com.company;

public class ManufacturingPlant implements Runnable {
    private Storage storage;

    public ManufacturingPlant(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        // every 3 seconds produce 5 rolls
        // if full wait an additional 15 seconds
        while(true) {

            try {
                Thread.sleep(3000);
                storage.binarySemaphore.acquire();
                if (!storage.isEmpty()) {
                    storage.takeRolls(5);
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
