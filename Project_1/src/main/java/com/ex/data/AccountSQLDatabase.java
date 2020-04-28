package com.ex.data;

import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;

public class AccountSQLDatabase implements GenericDAO<Account, Integer> {
    private final DatabaseConnection dc;

    public AccountSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
    }

    @Override
    public boolean add(Account newAccount) {
        return false;
    }

    @Override
    public Account findByID(Integer id) {
        return null;
    }

    @Override
    public boolean update(Integer id, Account newAccount) {
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }
}
