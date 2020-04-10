package com.ex;

public class Consumer implements Runnable {
    public String name;
    public Consumer(String name) {this.name=name;}
    private Doer doer= new Doer();
    @Override
    public void run() {
        try{
            doer.take();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
