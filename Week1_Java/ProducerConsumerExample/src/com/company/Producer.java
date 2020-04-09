package com.company;

public class Producer implements Runnable {
    Buffer buf;

    public Producer(Buffer b) {
        buf = b;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; ++i) {
                buf.add(i);
                System.out.println("Produced " + i);

                //Thread.sleep(500);
            }
            // Just so that the program can end gracefully
            buf.add(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
