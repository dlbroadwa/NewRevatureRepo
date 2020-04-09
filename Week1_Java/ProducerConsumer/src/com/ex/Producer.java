package com.ex;

public class Producer implements Runnable {
    public String employee;
    public Producer (String name){this.employee=name;}
    public Doer doer = new Doer();



    @Override
    public void run() {
       try {
           doer.make();
        } catch (InterruptedException e) {
           e.printStackTrace();
       }

    }

    public String getEmployee() {
        return employee;
    }
}
