package com.company.Banking;

import com.company.DataAccess.DAO;
import com.company.DataAccess.FileCRUD;
import com.company.Validation.Validate;

public class Bank implements BankActions{
    Validate validation = new Validate();
    DAO dataAccess = new FileCRUD();

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
