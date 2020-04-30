package com.game.service.accountservices;

import com.game.system.AuthenticationStatus;

public class CreationServiceImp implements CreationService{
    private final AccountDetailService accountDetailService;

    public CreationServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public void signUp(String username, String password, String email){
        if(accountDetailService.checkExist(username)||!checkValidity(username, password, email)){
            return;
        }
        accountDetailService.addAccount(username,password,email);
    }

    /**
     * further checks validity of given inputs based on restrictions
     * @param username username
     * @param password password
     * @param email email
     * @return true if all entries are valid
     */
    @Override
    public boolean checkValidity(String username, String password, String email) {
        return false;
    }

    @Override
    public void delete(String username) {
        if (accountDetailService.checkExist(username)){
            accountDetailService.removeAccount(username);
        }
    }

    @Override
    public void close() {
        accountDetailService.close();
    }
}