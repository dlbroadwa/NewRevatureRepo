package com.ex.Objects;

public class Keepers {

//Instant Variables
    private String usernameKey, passwordKey, firstname, lastname,action,time;

//Constructors
    public Keepers(){}

//Getters
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsernameKey() {
        return usernameKey;
    }

    public String getPasswordKey() {
        return passwordKey;
    }

    public String getAction(){
        return action;
    }

    public String getTime(){
        return time;
    }

//Setters
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsernameKey(String usernameKey) {
        this.usernameKey = usernameKey;
    }

    public void setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey;
    }

    public void setAction(String action){
        this.action = action;
    }

    public void setTime(String time){
        this.time = time;
    }

//To String for Tests
    public String toStringKeepers() {
        return "Keepers{" +
                "usernameKey='" + usernameKey + '\'' +
                ", passwordKey='" + passwordKey + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname +'}';
    }

    public String toStringTrans() {
        return "Transactions {" + "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", action='" + action + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
