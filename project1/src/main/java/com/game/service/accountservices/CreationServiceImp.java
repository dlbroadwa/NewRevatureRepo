package com.game.service.accountservices;

import com.game.data.MessageSQLRepo;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Deals with instantiating and deleting account objects
 */
public class CreationServiceImp implements CreationService{
    private final AccountDetailService accountDetailService;
    static final Logger logger = Logger.getLogger(MessageSQLRepo.class);

    /**
     * Uses functions from account service
     * @param accountDetailService accountService
     */
    public CreationServiceImp(AccountDetailService accountDetailService){
        BasicConfigurator.configure();
        this.accountDetailService = accountDetailService;
    }

    /**
     * Validates and add account
     * @param username desired username
     * @param password desired password
     * @param email chosen email
     * @return true if new user was created
     */
    @Override
    public boolean signUp(String username, String password, String email){
        if(accountDetailService.checkExist(username)){
            logger.debug("username exists");
            return false;
        }
        return accountDetailService.addAccount(username,password,email);
    }

    /**
     * deletes account if user exists
     * @param username selected user
     * @return true if delete succeeds
     */
    @Override
    public boolean delete(String username) {
        if (accountDetailService.checkExist(username)){
            logger.debug(username + "'s account was deleted");
            return accountDetailService.removeAccount(username);
        }
        return false;
    }
}
