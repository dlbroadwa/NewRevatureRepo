package com.company.Banking;

import com.company.DataAccess.DAO;
import com.company.Validation.Validate;
import com.sun.corba.se.spi.activation.Repository;

import java.util.List;

public class Bank implements BankActions{
    Validate validation = new Validate();

    private DAO<BankCustomer, Integer> repo;

    public Bank(DAO<BankCustomer, Integer> repo) {
        this.repo = repo;
    }

    public Bank(){};
    public List<BankCustomer> getAllCreators() {
        // this method may do some validation and authorization before handling
        // the request directly
        return this.repo.findAll();
    }

    public void deposit(int uID, char location) {
        boolean wasAdded = false;
        double amount = validation.checkDouble();
        if(location == 'c')
        {
            //Write to checking
            wasAdded = true;
        }
        else if(location == 's')
        {
            //write to saving
            wasAdded = true;
        }
        if(!(wasAdded))
        {
            System.out.println("Something went wrong while depositing your money.");
        }
        else
        {
            System.out.println("Your money was depositied successfully");
        }
    }

    public void withdraw(int uID, char location) {

    }

    public void transfer(int uID, char first, char second) {

    }

    public void checkBalance() {

    }
}
