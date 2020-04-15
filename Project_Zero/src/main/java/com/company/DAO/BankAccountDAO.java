package com.company.DAO;

import com.company.Banking.Account;
import com.company.Banking.Transaction;

import java.util.ArrayList;

public class BankAccountDAO implements DAO<Account> {
    private ArrayList<Account> accounts = new ArrayList<Account>();

    public BankAccountDAO() {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(0, 0.00, 20.00, "deposit"));
        transactions.add(new Transaction(1, 20.00, -4.55, "withdrawal"));
        accounts.add(new Account(0,15.55, transactions));
    }

    @Override
    public Integer save(Account obj) {
        return null;
    }

    @Override
    public ArrayList<Account> retrieveAll() {
        return null;
    }

    @Override
    public Account retrieve(Account obj) {
        return null;
    }

    @Override
    public void delete(Account obj) {

    }
}
