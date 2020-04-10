package com.revature;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String user_pin;
    private String user_id;
    private boolean is_active;
    private ArrayList<Account> accountsOfUser;

    public User (String firstName, String lastName, String phoneNumber, String user_pin, String user_id, boolean is_active){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.user_id = user_id;
        this.user_pin = user_pin;
        this.is_active = is_active;

    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUser_pin() {
        return user_pin;
    }

    public void setUser_pin(String user_pin) {
        this.user_pin = user_pin;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }






}
