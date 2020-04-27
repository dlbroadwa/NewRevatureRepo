package com.ex.models;

public class Account {//Start of Guests Class
//Instant Variables
    String name, email, password;
    Boolean isEmployee, isManager;
//Constructors
    public Account(){}

    public Account(String name, String email, String password, Boolean employee, Boolean manager){
        this.name = name;
        this.email = email;
        this.password = password;
        this.isEmployee = employee;
        this.isManager = manager;
    }
//Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public Boolean getManager() {
        return isManager;
    }

//Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }
}//End of Guests Class
