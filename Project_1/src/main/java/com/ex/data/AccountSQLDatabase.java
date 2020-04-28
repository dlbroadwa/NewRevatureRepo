package com.ex.data;

import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;
/**
 *   AccountSQlDatabase is used
 *   Created by: Perry Lee on April 28,2020
 *
 */
public class AccountSQLDatabase implements GenericDAO<Account, Integer> {//Start of AccountSQLDatabase Class

//Methods
    private final DatabaseConnection dc;

    public AccountSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
    }

    public boolean add(Account newAccount) {
        return false;
    }

    public Account findByID(Integer id) {
        return null;
    }

    public boolean update(Integer id, Account newAccount) {
        return false;
    }

    public boolean remove(Integer id) {
        return false;
    }

}//End of AccountSQLDatabase Class
