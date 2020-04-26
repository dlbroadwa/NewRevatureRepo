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
 * @param phonecarrier - the phone service for this person - allows for SMS messaging
 */
public abstract class Person {
    private String name;
    private String phone;
    private String emergencyphone;
    private PhoneCarrier phonecarrier;
    private Boolean allowTextNotifs;
    //private Team team;

    public Person() {
        this.setName("");
        this.setPhone("");
        this.setEmergencyPhone("");
        this.phonecarrier = PhoneCarrier.TMobile;
        this.allowTextNotifs = false;
    }

    public Person(String name, String phone, String emergencyphone, PhoneCarrier phonecarrier, boolean allowTextNotifs) {
        this.name = name;
        this.phone = phone;
        this.emergencyphone = emergencyphone;
        this.phonecarrier = phonecarrier;
        this.allowTextNotifs = allowTextNotifs;
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
    public PhoneCarrier getPhonecarrier() {
        return phonecarrier;
    }
    public void setPhonecarrier(PhoneCarrier phonecarrier) {
        this.phonecarrier = phonecarrier;
    }

    public void getAllowTextNotifs() {
        return allowTextNotifs;
    }

    public void setAllowTextNotifs(boolean allow) {
        this.allowTextNotifs = allow;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", emergencyphone='" + emergencyphone + '\'' +
                ", phonecarrier=" + phonecarrier +
                ", allowTextNotifs=" + allowTextNotifs +
                '}';
    }
}