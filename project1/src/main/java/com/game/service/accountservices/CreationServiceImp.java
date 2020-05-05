package com.game.service.accountservices;

public class CreationServiceImp implements CreationService{
    private final AccountDetailService accountDetailService;

    public CreationServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public boolean signUp(String username, String password, String email){
        if(accountDetailService.checkExist(username)){
            return false;
        }
        accountDetailService.addAccount(username,password,email);
        return true;
    }

    @Override
    public boolean delete(String username) {
        if (accountDetailService.checkExist(username)){
            if(accountDetailService.removeAccount(username)){
                return true;
            }
        }
        return false;
    }
}
