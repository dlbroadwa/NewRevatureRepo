package com.company.models;

public class Employee extends Thread {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println(name + " is working");

            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}
