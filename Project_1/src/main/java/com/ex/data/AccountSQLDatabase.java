package com.ex.data;

import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *   AccountSQlDatabase is used
 *   Created by: Perry Lee on April 28,2020
 *   Perry Lee: Added added DatabaseConnection, add method, findById method, update method, and remove method - April 28
 *   Paityn Maynard: Added findAll method - April 29
 *
 */
public class AccountSQLDatabase implements GenericDAO<Account, Integer> {//Start of AccountSQLDatabase Class
//Instant Variables
    private final DatabaseConnection dc;

//Constructors
    public AccountSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
    }

//Methods
    public List<Account> findAll() {
        return null;
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
