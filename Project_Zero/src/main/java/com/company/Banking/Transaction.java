package com.company.Banking;

import java.sql.Timestamp;

public class Transaction {
    private int transactionID = -1;
    private double previousBalance;
    private double transactionAmount;
    private String description;
    private Timestamp timeOfTransaction;

    public Transaction(int transactionID, double previousBalance, double transactionAmount, String description) throws IllegalArgumentException {
        if (transactionID < 0) throw new IllegalArgumentException("TransactionID cannot be less than zero!");
        this.transactionID = transactionID;
        this.previousBalance = previousBalance;
        this.transactionAmount = transactionAmount;
        this.description = description;
        this.timeOfTransaction = new Timestamp(System.currentTimeMillis());
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
        System.out.println("Transaction Amount:\t" + transactionAmount + "\nPrevious Balance:\t" + previousBalance + "\nUpdated Balance:\t" + getUpdatedBalance() + "\nDescription:\t" + description + "\nTime of Transaction:\t" + timeOfTransaction);
    }

    @Override
    public String toString() {
        return transactionID + "," + previousBalance + "," + getUpdatedBalance() + "," + transactionAmount + "," + description + "," + (timeOfTransaction.toString().split(".")[0]);
    }
}
