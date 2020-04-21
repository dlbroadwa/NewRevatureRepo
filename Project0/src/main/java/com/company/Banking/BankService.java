/**
 * Service class that implements BankI and handles business rules before reaching DAO
 */
package com.company.Banking;
import com.company.DataAccess.DAO;
import com.company.Validation.Validate;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class BankService implements BankI<Integer> {
    Validate validation = new Validate();

    private DAO<BankCustomer, Integer> repo;

    public BankService(DAO<BankCustomer, Integer> repo) {
        this.repo = repo;
    }

    public BankService(){};

    /**
     * Handles whole deposit process of bank app
     * @param uID Specified User ID
     * @param location Specified account they want to deposit into
     */
    public void deposit(Integer uID, char location) {
        boolean wasAdded = false;
        LocalDate currentDate = LocalDate.now();
        String action = "fail";

        // Verify user amount
        double amount = validation.checkDouble();
        // Grab current customer information
        BankCustomer currentCustomer = this.repo.findAccountById(uID);
        // Checks if they want to deposit into checkings or savings
        if(location == 'c')
        {
            // Update Checkings
            action = "Deposited $" + amount + " into checking";
            amount = currentCustomer.getCheckings() + amount;
            // Update Account Table Checking Column
            wasAdded = this.repo.updateOneAccount(uID, amount, "checkings");
        }
        else if(location == 's')
        {
            // Update Savings
            action = "Deposited $" + amount + " into savings";
            amount = currentCustomer.getSavings() + amount;
            // Update Account table Savings Column
            wasAdded = this.repo.updateOneAccount(uID, amount, "savings");
        }
        // Checks if account was updated if true then update transaction table
        if(!(wasAdded))
        {
            System.out.println("Something went wrong while depositing your money.");
        }
        else
        {
            // Add current transaction to table
            this.repo.addTransaction(uID, currentDate, action);
            System.out.println("Your money was deposited successfully");
        }
    }

    /**
     * Handles withdraw process of banking app
     * @param uID Specified User Id
     * @param location Specified Account they want to withdraw from
     */
    public void withdraw(Integer uID, char location) {
        boolean wasAdded = false;
        boolean isWithdrawable;
        LocalDate currentDate = LocalDate.now();
        String action = "fail";

        // Verify User amount
        double amount = validation.checkDouble();
        // Grab current customer information
        BankCustomer currentCustomer = this.repo.findAccountById(uID);

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
                wasAdded = this.repo.updateOneAccount(uID, amount, "checkings");
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
                wasAdded = this.repo.updateOneAccount(uID, amount, "savings");
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
            this.repo.addTransaction(uID, currentDate, action);
            System.out.println("Your money was withdrawn successfully");
        }
    }

    /**
     * Handles whole transfer process between the Checking and Savings accounts
     * @param uID User ID
     * @param first location they transferring from
     * @param second location they are transferring to
     */
    public void transfer(Integer uID, char first, char second) {
        BankCustomer currentCustomer = this.repo.findAccountById(uID);
        boolean isTransferable;
        boolean wasTransferred = false;
        LocalDate currentDate = LocalDate.now();
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
                double currentChecking = currentCustomer.getCheckings() - amount;
                double currentSaving = currentCustomer.getSavings() + amount;
                // Update database with new balances
                wasTransferred = this.repo.updateAccounts(uID, currentChecking, currentSaving);
            }
        }
        else
        {
            isTransferable = validation.isWithdrawable(amount, currentCustomer.getSavings());
            if(isTransferable)
            {
                action = "You transferred $" + amount + " from savings into checkings";
                double currentChecking = currentCustomer.getCheckings() + amount;
                double currentSaving = currentCustomer.getSavings() - amount;
                wasTransferred = this.repo.updateAccounts(uID, currentChecking, currentSaving);
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
            this.repo.addTransaction(uID, currentDate, action);
            System.out.println("Your money was transferred successfully");
        }
    }

    /**
     * Prints out current accounts balances of specified customer
     * @param uID specified user ID
     */
    public void checkBalance(Integer uID) {
        BankCustomer currentCustomer = this.repo.findAccountById(uID);
        System.out.println("Checking: " + currentCustomer.getCheckings());
        System.out.println("Savings: " + currentCustomer.getSavings() + "\n");
    }

    /**
     * Prints out whole transaction history of user
     * @param uID Spsecified user ID
     */
    public void viewTransactionHistory(Integer uID) {
        List<String> transactions = this.repo.findAllTransactionsById(uID);
        System.out.println("Date        Transaction");
        Collections.reverse(transactions);
        for(String action: transactions)
        {
            System.out.println(action);
        }
        System.out.println();
    }

}
