package com.revature;

import java.sql.Time;
import java.sql.Timestamp;


public class Transaction {
    private String transactionId;
    private Account account;
    private String typeOfTransaction;
    private Timestamp timestamp;
    private User user;
    private double amount;

    public Transaction (String tmpTransactionId, Account tmpAccount, String tmpTransactionType, Timestamp tmpDate, User tmpUser, double tmpAmount){
        this.account = tmpAccount;
        this.typeOfTransaction = tmpTransactionType;
        this.timestamp = tmpDate;
        this.user = tmpUser;
        this.amount = tmpAmount;
        this.transactionId = tmpTransactionId;
    }

    public Account getAccount() {
        return account;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private void displayTransactionDetails (){
        System.out.println("Transaction detail: ");
        System.out.println(this.timestamp.toString() + "," + this.account.getAccount_id() + "," + this.user.getUser_id() + "," + this.user.getFirstName() + " " + this.user.getLastName() + "," + String.format ("%.2f",this.getAmount()));
    }


    public static void main (String args[]){
        User tmpUser = new User ("id_123444480", "Bob" , "Jack", "2028882222", "6767" , true );
        Account tmpAccount = new Account ("acct_id_123456789", "id_123444480", "checking", 100.05);
        Timestamp tmpDate = new Timestamp (System.currentTimeMillis());

        Transaction tmpTransaction = new Transaction("trans_id_000001", tmpAccount,"depposit", tmpDate,tmpUser, 120);

        System.out.println("Transaction details: ");
        System.out.println (tmpTransaction.getTimestamp().toString()  + "," + tmpTransaction.transactionId + ","  +
                tmpTransaction.account.getAccount_id() + ","  + tmpTransaction.typeOfTransaction + "," + tmpTransaction.amount);


    }
}
