package com.ex.Objects;

/*Class Description:
    *This Class allows for the creation of Animal Objects that have a name,type,sex,age, and enclosure
 */

public class Animals {//Start of Animals Class

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
}//End of Animals Class
