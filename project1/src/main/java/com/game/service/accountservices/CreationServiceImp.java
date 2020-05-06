package com.game.service.accountservices;

import com.game.data.MessageSQLRepo;
import org.apache.log4j.Logger;

public class CreationServiceImp implements CreationService{
    private final AccountDetailService accountDetailService;
    static final Logger logger = Logger.getLogger(MessageSQLRepo.class);

    public CreationServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public boolean signUp(String username, String password, String email){
        if(accountDetailService.checkExist(username)){
            return false;
        }
        return accountDetailService.addAccount(username,password,email);
    }

    @Override
    public boolean delete(String username) {
        if (accountDetailService.checkExist(username)){
            return accountDetailService.removeAccount(username);
        }
        return false;
    }
}
