package com.revature;

public class Account {
    private String accountHolderId;
    private String accountType;
    private String account_id;
    private double balance;

    public Account (String accountID, String accountHolder, String type, double balance){
        this.account_id = accountID;
        this.accountHolderId = accountHolder;
        this.accountType = type;
        this.balance = balance;
    }

    public String getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(String accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
