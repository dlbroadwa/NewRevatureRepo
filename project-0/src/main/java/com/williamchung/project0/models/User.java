package com.williamchung.project0.models;

public class User {
    private int id;
    private String username;
    private String password;
    private Double checkingBalance;

    //Constructors
    public User (String username, String password, Double checkingBalance){
        this.username = username;
        this.password = password;
        this.checkingBalance = checkingBalance;
    }
    public User (String username, String password) {
        this(username, password, 0.00);
    } //Overloaded constructor to default checkingBalance to 0 when registering


    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(Double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
