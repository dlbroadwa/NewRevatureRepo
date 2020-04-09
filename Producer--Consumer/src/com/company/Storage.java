package com.company;

import java.security.InvalidParameterException;
import java.util.concurrent.Semaphore;

public class Storage {
    private final int maxStorage = 5000;
    private int numberOfSteelRolls = 0;
    public Semaphore binarySemaphore = new Semaphore(1, true);

    public int getNumberOfSteelRolls() {
        return numberOfSteelRolls;
    }

    public void addRolls(int amount) {
        if (amount < 0) throw new IllegalArgumentException("amount has to be greater than zero.");
        System.out.println("number of steel rolls is " + numberOfSteelRolls);
        System.out.println("adding " + amount + " rolls to storage");
        numberOfSteelRolls += amount;
        System.out.println("number of steel rolls is " + numberOfSteelRolls);
    }

    public int takeRolls(int amount) {
        if (amount < 0) throw new IllegalArgumentException("amount has to be greater than zero.");
        System.out.println("number of steel rolls is " + numberOfSteelRolls);
        System.out.println("taking " + amount + " rolls from storage");
        numberOfSteelRolls -= amount;
        System.out.println("number of steel rolls is " + numberOfSteelRolls);
        return amount;
    }

    public boolean isFull() {
        return (numberOfSteelRolls >= maxStorage);
    }

    public boolean isEmpty() {
        return numberOfSteelRolls == 0;
    }
}
