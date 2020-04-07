package com.company.Interfaces;

public interface BankActions {

    void deposit(double amount);

    void withdrawl(double amount);

    void transfer(double amount, char first, char second);

    void checkBalance();

}
