package com.game.models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Account {
    private String name;
    private String password;
    private	int balance;
    private boolean isAdmin;

    public Account(String name, String password){
        this.name = name;
        this.password = password;
        this.isAdmin=false;
    }

    public Account(String name, String password, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Account(String name, String password, boolean isAdmin, int deposit){
        this.name = name;
        this.password = password;
        this.balance = deposit;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
