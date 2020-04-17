package com.company.banking;

import java.sql.Timestamp;

public class Transaction {
    public static final int NEW_TRANSACTIONID = -1;
    private int transactionID;
    private double previousBalance;
    private double transactionAmount;
    private String description;
    private Timestamp timeOfTransaction;

    public Transaction(int transactionID, double previousBalance, double transactionAmount, String description, Timestamp timeOfTransaction) {
        this.transactionID = transactionID;
        this.previousBalance = previousBalance;
        this.transactionAmount = transactionAmount;
        this.description = description;
        this.timeOfTransaction = timeOfTransaction;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public double getPreviousBalance() {
        return previousBalance;
    }

    public double getUpdatedBalance() {
        return previousBalance + transactionAmount;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getTimeOfTransaction() { return timeOfTransaction; }

    public void printToScreen() {
        System.out.println("TransactionID:\t" + transactionID + "\nAmount:\t" + transactionAmount + "\nPrevious Balance:\t" + previousBalance + "\nUpdated Balance:\t" + getUpdatedBalance() + "\nDescription:\t" + description + "\nTime of Transaction:\t" + timeOfTransaction);
    }

    @Override
    public String toString() {
        return transactionID + "," + previousBalance + "," + getUpdatedBalance() + "," + transactionAmount + "," + description + "," + (timeOfTransaction.toString());
    }
}
