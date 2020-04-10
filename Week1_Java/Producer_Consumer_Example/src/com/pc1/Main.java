package com.pc1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BlockingQueue sharedQueue = new LinkedBlockingQueue();

        Thread ncMaker = new Thread(new NCMaker(sharedQueue));
        Thread ncDrinker = new Thread(new NCDrinker(sharedQueue));

        ncMaker.start();
        ncDrinker.start();

        /*try {
            ncDrinker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Out of Nuka Cola!");*/
    }
}
