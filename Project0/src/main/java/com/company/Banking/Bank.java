package com.company.Banking;

import com.company.DataAccess.DAO;
import com.company.Validation.Validate;
import com.sun.corba.se.spi.activation.Repository;

import java.time.LocalDate;
import java.util.List;

public class Bank implements BankActions{
    Validate validation = new Validate();

    private DAO<BankCustomer, Integer> repo;

    public Bank(DAO<BankCustomer, Integer> repo) {
        this.repo = repo;
    }

    public Bank(){};

    public void deposit(int uID, char location) {
        boolean wasAdded = false;
        LocalDate currentDate = LocalDate.now();
        String action = "fail";
        double amount = validation.checkDouble();
        BankCustomer currentCustomer = this.repo.findAccountById(uID);
        if(location == 'c')
        {
            //Update Checkings
            action = "Deposited $" + amount + " into checking";
            amount = currentCustomer.getCheckings() + amount;
            wasAdded = this.repo.updateOneAccount(uID, amount, "checkings");
        }
        else if(location == 's')
        {
            //Update Savings
            action = "Deposited $" + amount + " into savings";
            amount = currentCustomer.getSavings() + amount;
            wasAdded = this.repo.updateOneAccount(uID, amount, "savings");
        }
        if(!(wasAdded))
        {
            System.out.println("Something went wrong while depositing your money.");
        }
        else
        {
            this.repo.addTransaction(uID, currentDate, action);
            System.out.println("Your money was deposited successfully");
        }
    }

    public void withdraw(int uID, char location) {
        boolean wasAdded = false;
        boolean isWithdrawable;
        double amount = validation.checkDouble();
        LocalDate currentDate = LocalDate.now();
        String action = "fail";
        BankCustomer currentCustomer = this.repo.findAccountById(uID);

        if(location == 'c')
        {
            //Update Checkings
            isWithdrawable = validation.isWithdrawable(amount, currentCustomer.getCheckings());
            if(isWithdrawable)
            {
                action = "Withdrew $" + amount + " from checking";
                amount = currentCustomer.getCheckings() - amount;
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
                wasAdded = this.repo.updateOneAccount(uID, amount, "savings");
            }
            else
            {
                System.out.println("That is not a valid amount");
            }

        }
        if(!(wasAdded))
        {
            System.out.println("Something went wrong while withdrawing your money.");
        }
        else
        {
            this.repo.addTransaction(uID, currentDate, action);
            System.out.println("Your money was withdrawn successfully");
        }

    }

    public void transfer(int uID, char first, char second) {
        BankCustomer currentCustomer = this.repo.findAccountById(uID);
        boolean isTransferable;
        boolean wasTransferred = false;
        LocalDate currentDate = LocalDate.now();
        String action = "fail";
        double amount = validation.checkDouble();
        if(first == 'c')
        {
            isTransferable = validation.isWithdrawable(amount, currentCustomer.getCheckings());
            if(isTransferable)
            {
                action = "You transferred $" + amount + " from checkings into savings";
                double currentChecking = currentCustomer.getCheckings() - amount;
                double currentSaving = currentCustomer.getSavings() + amount;
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
            System.out.println("Something went wrong while transferring your money.");
            System.out.println("Please call an employee for help");
        }
        else
        {
            this.repo.addTransaction(uID, currentDate, action);
            System.out.println("Your money was transferred successfully");
        }


    }

    public void checkBalance(int uID) {
        BankCustomer currentCustomer = this.repo.findAccountById(uID);
        System.out.println("Checking: " + currentCustomer.getCheckings());
        System.out.println("Savings: " + currentCustomer.getSavings());
    }

    public void viewTransactionHistory(int uID) {
        List<String> transactions = this.repo.findAllTransactionsById(uID);
        System.out.println("Date        Transaction");
        for(String action: transactions)
        {
            System.out.println(action);
        }
    }

}
