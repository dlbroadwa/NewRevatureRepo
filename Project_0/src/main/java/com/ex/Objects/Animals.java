package com.ex.Objects;

public class Animals {

//Instant Variables
    public String animalName;
    public String animalType;
    public String sex;
    public int age;
    public int enclosure;

//Constructor
    public Animals(){}

    public Animals(String animalName, String animalType, String sex, int age, int enclosure ) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.sex = sex;
        this.age = age;
        this.enclosure = enclosure;
    }



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

//To String for Tests
    public String toStringAll() {
        return "Animals{" +
                "animalName='" + animalName + '\'' +
                ", animalType='" + animalType + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", enclosure=" + enclosure +
                '}';
    }

    public String toStringSpecific() {
        return "Animals{" + " animalType='" + animalType + ", enclosure=" + enclosure +'}';
    }
}
