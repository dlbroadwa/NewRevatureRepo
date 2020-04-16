package com.company.DAO;

import com.company.loginAccounts.LoginAccount;

import java.util.ArrayList;

public class LoginAccountDAO implements DAO<LoginAccount, String> {

    @Override
    public String save(LoginAccount obj) {
        return null;
    }

    @Override
    public ArrayList<LoginAccount> retrieveAll() {
        return null;
    }

    @Override
    public LoginAccount[] retrieveByID(String s) {
        return null;
    }

    @Override
    public void delete(LoginAccount obj) {

    }

    @Override
    public void update(LoginAccount newObj) {

    }
}
