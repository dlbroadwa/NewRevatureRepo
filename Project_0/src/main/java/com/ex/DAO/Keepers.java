package com.ex.DAO;

public class Keepers {

//Instant Variables
    private String usernameKey, passwordKey, firstname, lastname,action;

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

}
