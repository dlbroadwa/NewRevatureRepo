package com.game.service.accountservices;

import com.game.models.Account;

public interface AccountDetailService {
    boolean checkExist(String username);
    Account findByID(String username);
    boolean checkCredentials(String username, String password);
    Account getCurr();

    void addAccount(String username, String password, String email);
}
