package com.company;

public class Consumer implements Runnable {
    Buffer buf;

    public Consumer(Buffer b) {
        buf = b;
    }

    @Override
    public void run() {
        try {
            int value = 0;
            while (value != -1) {
                value = buf.remove();
                if (value != -1)
                    System.out.println("Consumed " + value);

                //Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
