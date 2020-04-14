package com.revature;

public abstract class BankAccountDAO {
    abstract void printAccountDetails (String accountId);
    abstract void updateAccountBalance (String accountId, double newBalance);
    abstract void insertAccount(Account account);

}
