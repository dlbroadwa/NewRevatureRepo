package com.game.service.accountservices;

import com.game.models.Account;

import java.util.List;

public interface AccountDetailService {
    boolean checkExist(String username);
    Account findByID(String username);
    boolean checkCredentials(String username, String password);
    Account getCurr();
    List<String> getAccountList();
    void addAccount(String username, String password, String email);
    void removeAccount(String username);
    void close();
    void update(Account obj);
}
