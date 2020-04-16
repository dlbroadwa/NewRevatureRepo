package com.ex.animal_dao;

public class Animals {

//Instant Variables
    public String animalName;
    public String animalType;
    public String sex;
    public int age;
    public int enclosure;

//Constructor
    public Animals(){}

//Getters
    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String  getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public int getEnclosure() {
        return enclosure;
    }

//Setters
    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEnclosure(int enclosure) {
        this.enclosure = enclosure;
    }
}
