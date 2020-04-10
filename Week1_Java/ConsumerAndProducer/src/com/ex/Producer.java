package com.ex;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    Scanner s = new Scanner(System.in);
    String product = new String();
    private BlockingQueue buffer;
    private BlockingQueue num;

    public Producer(BlockingQueue buffer,BlockingQueue num){
        this.buffer = buffer;
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("What product are you selling?");
        this.product = s.nextLine();
        try{
            Thread.sleep(1000);
            for(int i=0; i<5; i++)
            {
                System.out.println("You produced "+ i + " "+product);
                buffer.put(product);
                num.put(i);
            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}