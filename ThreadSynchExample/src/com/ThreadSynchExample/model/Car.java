package com.ThreadSynchExample.model;

import java.util.LinkedList;
import java.util.Random;

public class Car {
    LinkedList<String> cars = new LinkedList<String>();
    String possibleCars[] = {"Toyota", "Honda", "Cheverolet", "Ford", "Dodge"};
    int capacity = 3;

    public void produceCar() throws InterruptedException {
        while (true) {
            synchronized (this)
            {
                while(cars.size() == capacity)
                    wait();
                Random rand = new Random();
                int randInt = rand.nextInt(possibleCars.length);
                cars.add(possibleCars[randInt]);
                System.out.printf("PRODUCER - produced %s cars \n", possibleCars[randInt]);

                notify();

                Thread.sleep(1000);
            }
        }
    }

    public void consumeCar() throws InterruptedException {
        while(true){
            synchronized (this)
            {
                while(cars.size() == 0)
                    wait();

                String first = cars.removeFirst();
                System.out.printf("CONSUMER - consumed: %s \n", first);

                notify();
                Thread.sleep(1000);
            }
        }
    }
}
