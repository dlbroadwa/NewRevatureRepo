package com.ex;
import java.util.LinkedList;

public class Consumer implements Runnable{
    private String name;
    public Consumer(String name){this.name=name;}


    @Override
    public void run() {
        for(int i = 0; i < 10 ; i++){
            System.out.println("Consumer " + name + " has control now on iteration " + i + "." );
            try{
                Thread.sleep(2000);
            }catch(InterruptedException ex){
                ex.printStackTrace();
                break;
            }
        }
    }
}
