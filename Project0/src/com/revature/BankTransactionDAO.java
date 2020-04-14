package com.revature;

import java.util.LinkedList;

public abstract class BankTransactionDAO {
    public abstract void addTransaction(Transaction transaction);
    public abstract String retrieveOneTransaction (String transactionId);
    public abstract LinkedList <String> retrieveTransactionByAccount (Account account);
}
