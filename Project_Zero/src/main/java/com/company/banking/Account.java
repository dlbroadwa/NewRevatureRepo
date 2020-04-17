package com.company.banking;

import java.util.ArrayList;

public class Account {
    private int accountID = -1;
    private double currentBalance;
    private ArrayList<Transaction> balanceHistory = null;

    public Account(int accountID) throws IllegalArgumentException {
        if (accountID < 1) throw new IllegalArgumentException("AccountID has to be greater than zero.");
        this.accountID = accountID;
    }

    public Account(int accountID, double currentBalance, ArrayList<Transaction> balanceHistory) {
        this(accountID);
        this.currentBalance = currentBalance;
        this.balanceHistory = balanceHistory;
    }

    public void processTransaction(Transaction transaction) {
        currentBalance = transaction.getUpdatedBalance();
        balanceHistory.add(transaction);
    }

    public int getAccountID() {
        return accountID;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public ArrayList<Transaction> getBalanceHistory() {
        return balanceHistory;
    }

    public void printToScreen() {
        System.out.println("\nAccount ID:\t" + accountID + "\nCurrent Balance:\t" + currentBalance + "\nBalance History:");
        for (Transaction transaction: balanceHistory) {
            System.out.println();
            transaction.printToScreen();
        }
        System.out.println();
    }
    @Override
    public String toString() {

        return accountID + "," + currentBalance + "," + balanceHistory.size() + "," + balanceHistory.toString();
    }
}
