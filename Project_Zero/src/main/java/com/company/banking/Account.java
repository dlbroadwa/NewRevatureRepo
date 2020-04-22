package com.company.banking;

import java.util.ArrayList;

/***
 * This class models a bank account.
 *
 * @author Shawyn Kane
 */
public class Account {
    private int accountID = -1;
    private double currentBalance;
    private ArrayList<Transaction> balanceHistory = null;

    /***
     * TODO write description for constructor for Account class
     *
     * @author Shawyn Kane
     */
    public Account(int accountID) throws IllegalArgumentException {
        if (accountID < 1) throw new IllegalArgumentException("AccountID has to be greater than zero.");
        this.accountID = accountID;
    }

    /***
     * TODO write description for constructor for Account class
     *
     * @author Shawyn Kane
     */
    public Account(int accountID, double currentBalance, ArrayList<Transaction> balanceHistory) {
        this(accountID);
        this.currentBalance = currentBalance;
        this.balanceHistory = balanceHistory;
    }

    /***
     *
     * @return accountID
     */
    public int getAccountID() {
        return accountID;
    }

    /***
     *
     * @return currentBalance
     */
    public double getCurrentBalance() {
        return currentBalance;
    }

    /***
     *
     * @return balanceHistory
     */
    public ArrayList<Transaction> getBalanceHistory() {
        return balanceHistory;
    }

    /***
     *
     * @param currentBalance
     */
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    /***
     *
     *
     * This method prints the accountID and current balance to the console in the following format:
     * """
     * AccountID:   ...
     * Current Balance: ...
     * """
     * @author Shawyn Kane
     */
    public void printCurrentBalanceToScreen() {
        System.out.println("\nAccount ID:\t" + accountID + "\nCurrent Balance:\t" + currentBalance);
    }

    /***
     *
     *
     * This method prints the "balance history" of the account in th following format:
     * """
     * Balance History:
     *
     * (calls the transaction.printToScreen() for each transaction associated with the accountID)
     * """
     * @author Shawyn Kane
     */
    public void printBalanceHistory() {
        System.out.println("Balance History:");
        for (Transaction transaction: balanceHistory) {
            System.out.println();
            transaction.printToScreen();
        }
        System.out.println();
    }

    /***
     * This method prints the accountID, current balance, and balance history of the account to the console by calling the printCurrentBalanceToScreen() and printBalanceHistory() methods.
     *
     * @author Shawyn Kane
     */
    public void printToScreen() {
        printCurrentBalanceToScreen();
        System.out.println();
        printBalanceHistory();
    }

    /***
     *
     * Returns a string of the accountID, current balance, number of transactions in balance history, and the toString() of the array list containing the transactions for the balance history, all of which separated by commas.
     * @author Shawyn Kane
     */
    @Override
    public String toString() {

        return accountID + "," + currentBalance + "," + balanceHistory.size() + "," + balanceHistory.toString();
    }
}
