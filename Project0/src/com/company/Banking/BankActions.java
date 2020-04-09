package com.company.Banking;

public interface BankActions {

    void deposit(double amount);

    void withdraw(double amount);

    void transfer(double amount, char first, char second);

    void checkBalance();

}
