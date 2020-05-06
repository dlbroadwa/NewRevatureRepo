package com.ex.models;

import java.util.Objects;

/**
 * Account is used to set and get information of users logging in
 * Created by: Paityn Maynard on April 25 2020
 *      Paityn Maynard: [Added name,email,password,isEmployee, and isManager (With Getters and Setters)
 *                       Created Accounts Constructors]-April 25
 */

public class Account {//Start of Account Class
//Instant Variables
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return isEmployee == account.isEmployee &&
                isManager == account.isManager &&
                Objects.equals(name, account.name) &&
                Objects.equals(email, account.email) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, isEmployee, isManager);
    }
}//End of Account Class
