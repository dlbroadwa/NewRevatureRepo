package com.game.service.accountservices;

import com.game.system.AuthenticationStatus;
/**
 * Class that implements authentication service which is the log in process
 */
public class UserAuthenticationImp implements UserAuthentication{
    private AccountDetailService accountDetailService;

    public UserAuthenticationImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public void authenticate(String username, String password, AuthenticationStatus onStatusChange) {
        //check if account password matches
        if(accountDetailService.checkCredentials(username,password)){
            onStatusChange.authStatus(username,true);
        }
        else {
            onStatusChange.authStatus(null,false);
        }
    }
}
