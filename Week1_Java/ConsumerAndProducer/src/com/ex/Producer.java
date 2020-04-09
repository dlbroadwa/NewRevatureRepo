package com.ex;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    Scanner s = new Scanner(System.in);
    String product = new String();
    private BlockingQueue buffer;

    public Producer(BlockingQueue buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("What product are you selling?");
        this.product = s.nextLine();
        try{
            buffer.put(product);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}