package com.company.Classes;

import com.company.Interfaces.BankActions;

public class BankCustomer implements BankActions {

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private double savings;

    private double checkings;

    private int id;

    public BankCustomer(){

    }

    public BankCustomer(String fName, String lName, String uName, String pWord, double sving, double ckings, int identity){

        this.firstName = fName;
        this.lastName = lName;
        this.userName = uName;
        this.password = pWord;
        this.savings = sving;
        this.checkings = ckings;
        this.id = identity;
    }


    @Override
    public void deposit(double amount) {


    }

    @Override
    public void withdrawl(double amount) {

    }

    @Override
    public void transfer(double amount, char firstLocation, char endLocation) {

    }

    @Override
    public void checkBalance() {

    }
}
