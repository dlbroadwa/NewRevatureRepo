package com.ex.MainAndMenu;

public abstract class Runner {

    String[] animals;

    public abstract void run();

    public String[] getAnimals() {
        return animals;
    }

    public void setAnimals(String[] animals) {
        this.animals = animals;
    }

}
