package com.ex;

public class Producer implements Runnable {
    public String employee;
    public Producer (String name){this.employee=name;}
    private Doer doer = new Doer();
    @Override
    public void run() {

       try {
           doer.make();
        } catch (InterruptedException e) {
           e.printStackTrace();
       }

    }
}
