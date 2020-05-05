package com.ex.models;
/**
 * Account is used to set and get information of users logging in
 * Created by: Paityn Maynard on April 25 2020
 *      Paityn Maynard: [Added name,email,password,isEmployee, and isManager (With Getters and Setters)
 *                       Created Accounts Constructors]-April 25
 */

public class Account {//Start of Account Class
//Instance Variables
    private String name;
    private String email;
    private String password;
    private boolean isEmployee;
    private boolean isManager;
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

    public boolean getEmployee() {
        return isEmployee;
    }

    public boolean getManager() {
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

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}//End of Account Class
