package com.game.service.accountservices;

import com.game.data.AccountSQLRepo;
import com.game.models.Account;

import java.util.List;

public class AccountDetailServiceImp implements AccountDetailService {
    List<String> accountList;
    AccountSQLRepo arepo;
    Account curr;

    public AccountDetailServiceImp(AccountSQLRepo accountSQLRepo){
        arepo = accountSQLRepo;
        accountList = arepo.findAllID();
    }

    @Override
    public boolean checkExist(String username) {
        return accountList.contains(username);
    }

    @Override
    public Account findByID(String username) {
        return arepo.findById(username);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        Account temp = findByID(username);
        if (temp.getPassword().equals(password)){
            curr=temp;
            return true;
        }
        return false;
    }

    @Override
    public Account getCurr() {
        return curr;
    }

    @Override
    public void addAccount(String username, String password, String email) {
        curr = new Account(username,password,email);
        accountList.add(username);
        arepo.save(curr);
    }
}
