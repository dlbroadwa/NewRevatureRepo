package com.game.data;

import com.game.models.Account;
import com.game.utils.ConnectionUtils;

import java.util.List;

public class AccountSQLRepo implements Repository<Account,String> {
    private List<Account> accountList;
    private ConnectionUtils connectionUtils;

    public AccountSQLRepo(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public Account findById(String s) {
        return null;
    }

    @Override
    public List findAll() {
        //Only gets the ones that are online
        return null;
    }

    @Override
    public String save(Account obj) {
        return null;
    }

    @Override
    public void update(Account newObj, String s) {

    }

    @Override
    public void delete(Account obj) {

    }
}
