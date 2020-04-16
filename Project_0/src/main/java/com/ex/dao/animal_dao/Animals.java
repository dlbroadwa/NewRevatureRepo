package com.ex.dao.animal_dao;

public class Animals {
    public String animalName;
    public String animalType;
    public String sex;
    public int age;
    public int enclosure;

    public Animals(){}

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String  getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(int enclosure) {
        this.enclosure = enclosure;
    }
}
