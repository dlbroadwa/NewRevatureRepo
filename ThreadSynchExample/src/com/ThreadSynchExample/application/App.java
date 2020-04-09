package com.ThreadSynchExample.application;

import com.ThreadSynchExample.model.Car;

public class App {
    public static void main(String[] args) {
        Car car = new Car();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    car.produceCar();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    car.consumeCar();
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
