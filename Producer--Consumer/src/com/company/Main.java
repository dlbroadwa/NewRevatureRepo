package com.company;

public class Main {

    public static void main(String[] args) {
        Storage storage = new Storage();
        Thread mill = new Thread(new SteelRollingMill(storage));
        Thread plant = new Thread(new ManufacturingPlant(storage));
        mill.start();
        plant.start();
    }
}
