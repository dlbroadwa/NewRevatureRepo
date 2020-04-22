/**
 * Service class that implements BankI and handles business rules before reaching DAO
 */
package com.company.Banking;
import com.company.DataAccess.DAOI;
import com.company.Validation.Validate;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class BankService implements BankI {
    Validate validation = new Validate();

    private DAOI<BankCustomer, Integer> repo;

    private BankCustomer currentCustomer;
    public BankService(DAOI<BankCustomer, Integer> repo, BankCustomer customer) {

        this.repo = repo;
        currentCustomer = customer;
        currentCustomer = this.repo.findAccountById(currentCustomer.getId());
    }

    public BankService(){};


    /**
     * Handles whole deposit process of bank com.company.app
     * @param location Specified account they want to deposit into
     */
    public void deposit(char location) {
        boolean wasAdded = false;
        LocalDate currentDate = LocalDate.now();
        String action = "fail";
        
        // Verify user amount
        double amount = validation.checkDouble();
        boolean isDepositable = validation.isDepositable(amount);
        if(!(isDepositable))
        {
            System.out.println("Sorry that is not a valid amount");
        }
        else{
            // Checks if they want to deposit into checkings or savings
            if(location == 'c')
            {
                // Update Checkings
                action = "Deposited $" + amount + " into checking";
                amount = currentCustomer.getCheckings() + amount;
                // Update Account Table Checking Column
                wasAdded = this.repo.updateOneAccount(currentCustomer.getId(), amount, "checkings");
            }
            else if(location == 's')
            {
                // Update Savings
                action = "Deposited $" + amount + " into savings";
                amount = currentCustomer.getSavings() + amount;
                // Update Account table Savings Column
                wasAdded = this.repo.updateOneAccount(currentCustomer.getId(), amount, "savings");
            }
            // Checks if account was updated if true then update transaction table
            if(!(wasAdded))
            {
                System.out.println("Something went wrong while depositing your money.");
            }
            else
            {
                // Add current transaction to table
                this.repo.addTransaction(currentCustomer.getId(), currentDate, action);
                if(location == 'c')
                {
                    currentCustomer.setCheckings(amount);
                }
                else
                {
                    currentCustomer.setSavings(amount);
                }
                System.out.println("Your money was deposited successfully");
            }
        }
    }

    /**
     * Handles withdraw process of banking com.company.app
     * @param location Specified Account they want to withdraw from
     */
    public void withdraw(char location) {
        boolean wasAdded = false;
        boolean isWithdrawable;
        LocalDate currentDate = LocalDate.now();
        String action = "fail";

        // Verify User amount
        double amount = validation.checkDouble();
        if(location == 'c')
        {
            // Update Checkings
            // Check to see if amount specified is a valid withdrawal amount
            isWithdrawable = validation.isWithdrawable(amount, currentCustomer.getCheckings());
            if(isWithdrawable)
            {
                action = "Withdrew $" + amount + " from checking";
                amount = currentCustomer.getCheckings() - amount;
                // Update checking column with new amount
                wasAdded = this.repo.updateOneAccount(currentCustomer.getId(), amount, "checkings");
            }
            else
            {
                System.out.println("That is not a valid amount");
            }
        }
        else if(location == 's')
        {
            //Update Savings
            isWithdrawable = validation.isWithdrawable(amount, currentCustomer.getSavings());
            if(isWithdrawable)
            {
                action = "Withdrew $" + amount + " from savings";
                amount = currentCustomer.getSavings() - amount;
                // Update saving column
                wasAdded = this.repo.updateOneAccount(currentCustomer.getId(), amount, "savings");
            }
            else
            {
                System.out.println("That is not a valid amount");
            }
        }
        if(!(wasAdded))
        {
            System.out.println("\nSomething went wrong while withdrawing your money.");
        }
        else
        {
            this.repo.addTransaction(currentCustomer.getId(), currentDate, action);
            if(location == 'c')
            {
                currentCustomer.setCheckings(amount);
            }
            else
            {
                currentCustomer.setSavings(amount);
            }
            System.out.println("Your money was withdrawn successfully");
        }
    }

    /**
     * Handles whole transfer process between the Checking and Savings accounts
     * @param first location they transferring from
     * @param second location they are transferring to
     */
    public void transfer(char first, char second) {
        boolean isTransferable;
        boolean wasTransferred = false;
        LocalDate currentDate = LocalDate.now();
        double currentChecking = currentCustomer.getCheckings();
        double currentSaving = currentCustomer.getSavings();
        String action = "fail";

        // Check to see if user input a valid number
        double amount = validation.checkDouble();
        // Check if user wants to transfer from checking or saving account
        if(first == 'c')
        {
            // Check if user is able to transfer specified amount from acount
            isTransferable = validation.isWithdrawable(amount, currentCustomer.getCheckings());
            if(isTransferable)
            {
                action = "You transferred $" + amount + " from checkings into savings";
                // Update both accounts with new balance
                currentChecking = currentCustomer.getCheckings() - amount;
                currentSaving = currentCustomer.getSavings() + amount;
                // Update database with new balances
                wasTransferred = this.repo.updateAccounts(currentCustomer.getId(), currentChecking, currentSaving);
            }
        }
        else
        {
            isTransferable = validation.isWithdrawable(amount, currentCustomer.getSavings());
            if(isTransferable)
            {
                action = "You transferred $" + amount + " from savings into checkings";
                currentChecking = currentCustomer.getCheckings() + amount;
                currentSaving = currentCustomer.getSavings() - amount;
                wasTransferred = this.repo.updateAccounts(currentCustomer.getId(), currentChecking, currentSaving);
            }
        }
        if(!(wasTransferred))
        {
            System.out.println("\nSomething went wrong while transferring your money.");
            System.out.println("Please call an employee for help");
        }
        else
        {
            // Add transaction to data base
            this.repo.addTransaction(currentCustomer.getId(), currentDate, action);
            currentCustomer.setCheckings(currentChecking);
            currentCustomer.setSavings((currentSaving));
            System.out.println("Your money was transferred successfully");
        }
    }

    /**
     * Prints out current accounts balances of current customer
     */
    public void checkBalance() {
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Checking: " + df.format(currentCustomer.getCheckings()));
        System.out.println("Savings: " + df.format(currentCustomer.getSavings()) + "\n");
    }

    /**
     * Prints out whole transaction history of user
     */
    public void viewTransactionHistory() {
        List<String> transactions = this.repo.findAllTransactionsById(currentCustomer.getId());
        System.out.println("Date        Transaction");
        Collections.reverse(transactions);
        for(String action: transactions)
        {
            System.out.println(action);
        }
        System.out.println();
    }

}
