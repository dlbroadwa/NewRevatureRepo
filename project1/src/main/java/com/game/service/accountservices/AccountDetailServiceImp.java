package com.game.service.accountservices;

import com.game.data.Repository;
import com.game.models.Account;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDetailServiceImp implements AccountDetailService {
    List<String> accountList;
    Map<String, Account> accountMap;
    Repository<Account,String> arepo;

    public AccountDetailServiceImp(Repository accountSQLRepo){
        arepo = accountSQLRepo;
        accountList = arepo.findAllID();
        accountMap = Collections.synchronizedMap(new HashMap<>());
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
            accountMap.put(username,temp);
            return true;
        }
        return false;
    }

    @Override
    public Account getAccount(String username) {
        return accountMap.get(username);
    }

    @Override
    public List<String> getAccountList() {
        return accountList;
    }

    @Override
    public void addAccount(String username, String password, String email) {
        Account temp = new Account(username,password,email);
        accountList.add(username);
        arepo.save(temp);
        accountMap.put(username,temp);
    }

    /**
     * Admin level method
     * @param username username
     */
    @Override
    public void removeAccount(String username) {
        if (username.equals("admin")){
            //cannot delete admin account
            return;
        }
        accountList.remove(username);
        arepo.delete(username);
    }

    @Override
    public void update(Account obj) {
        arepo.update(obj,obj.getName());
    }

    @Override
    public void logOff(String username) {
        accountMap.remove(username);
    }

}
