package com.company.Banking;

public interface BankActions {

    public void deposit(int uID, char location);

    public void withdraw(int uID, char location);

    public void transfer(int uID, char first, char second);

    public void checkBalance();
}
