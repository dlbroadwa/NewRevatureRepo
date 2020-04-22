package com.company.banking;

/**
 * Bank interface that will have methods that need to be implemented by the banking service
 */
public interface BankI{

    void deposit(char location);

    void withdraw(char location);

    void transfer(char first, char second);

    void checkBalance();

    void viewTransactionHistory();
}
