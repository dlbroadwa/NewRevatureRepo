package com.company.Banking;

public abstract class BankActions {

    public void deposit(int uID, double amount, double amountInAccount, char location);

    public abstract void withdraw(int uID, double amount, double amountInAccount, char location);

    public abstract void transfer(int uID, double amountInFirstLocation, double amount in char first, char second);

    public abstract void checkBalance();

}
