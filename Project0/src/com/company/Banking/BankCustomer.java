package com.company.Banking;

public class BankCustomer {

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
}
