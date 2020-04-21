package com.company.Banking;

/**
 * Bank interface that will have methods that need to be implemented by the banking service
 * @param <ID> User ID taken form DB
 */
public interface BankI<ID>{

    void deposit(ID uID, char location);

    void withdraw(ID uID, char location);

    void transfer(ID uID, char first, char second);

    void checkBalance(ID uID);

    void viewTransactionHistory(ID uID);
}
