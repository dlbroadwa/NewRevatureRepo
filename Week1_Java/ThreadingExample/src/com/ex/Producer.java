package com.ex;

public class Producer extends Thread {
    private String name;

    public Producer(String name){ this.name = name;}
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println(name + " is working on iteration " + i + ".");

            try{
                Thread.sleep(200);
            }catch(InterruptedException ex){
                ex.printStackTrace();
                break;
            }
        }

    }

}
