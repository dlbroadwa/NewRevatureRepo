package com.ex.model;

/**
 * This class is for the abstraction of a Person entity - to be extended to
 * Player for a more defined set of data.  Coaches will be a Person entity, where
 * players will be of type Player.
 *
 * @param name - the name of the person
 * @param phone - the phone number of the person
 * @param emergencyphone - the emergency contact number for person
 * @param team - the team this person belongs to
 */
public abstract class Person {
    private String name;
    private String phone;
    private String emergencyphone;
    //private Team team;

    public Person() {
        this.setName("");
        this.setPhone("");
        this.setEmergencyPhone("");
    }

    public Person(String name, String phone, String emergencyphone) {
        this.name = name;
        this.phone = phone;
        this.emergencyphone = emergencyphone;
    }

    /* =================    GET & SET   ======================= */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergencyPhone() {
        return emergencyphone;
    }

    public void setEmergencyPhone(String emergencyphone) {
        this.emergencyphone = emergencyphone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", emergencyphone='" + emergencyphone + '\'' +
                '}';
    }
}
