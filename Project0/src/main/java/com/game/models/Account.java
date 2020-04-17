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

    public void addCredits(int deposit){
        balance+=deposit;
    }
    public void spendCredits(int request){
        if (balance>=request) {
            balance -= request;
        }else{
            System.out.println("Not enough funds");
        }
    }
}
