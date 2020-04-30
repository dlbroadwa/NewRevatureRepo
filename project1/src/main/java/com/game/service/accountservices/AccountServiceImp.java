package com.game.service.accountservices;

import com.game.data.Repository;
import com.game.models.Account;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Services both the account repo and the message repo
 * Possesses an account/message list for quicker access
 */
public class AccountServiceImp {
    List<String> accountList;
    Account curr;
    Repository<Account, String> arepo;
    static final Logger logger = Logger.getLogger(AccountServiceImp.class);

    /**
     * Allows service to utilize the repos
     * @param arepo account repo
     */
    public AccountServiceImp(Repository<Account, String> arepo) {
        this.arepo = arepo;
        accountList = this.arepo.findAllID();
    }

    /**
     * created mainly for testing purposes for access outside the class
     */
    public Account getCurr(){
        return curr;
    }

    /**
     * get account information of requested user
     * @param username username of account you wish to look up
     */
    public void getAccountInfo(String username) {
        Account temp = findAccount(username);
        if (temp!=null) {
            logger.debug(temp.getName()+"\t"+temp.getPassword()+"\t"+temp.getBalance()+
                    "\t"+(temp.getIsAdmin()?"Admin":"Player"));
        }
    }

    /**
     * prints out the current user's username, password, balance, and account type
     */
    public void getAccountInfo() {
        logger.debug(curr.getName()+"\t"+curr.getPassword()+"\t"+curr.getBalance()+
                "\t"+(curr.getIsAdmin()?"Admin":"Player"));
    }

    /**
     * Changes current user's password
     * @param newPassword desired password
     */
    public void changePassword(String newPassword){
        curr.setPassword(newPassword);
        repo.update(curr, curr.getName());
    }

    /**
     * deposit credits into current account
     * @param deposit credits you want to deposit
     */
    public void depositM(int deposit) {
        curr.addCredits(deposit);
        logger.debug("Your balance is now " + curr.getBalance());
        repo.update(curr, curr.getName());
    }

    /**
     * request credits from current account
     * and prints remaining balance
     * @param request amount you want to withdraw
     * @return true if the transaction is successful
     */
    public boolean spendC(int request) {
        if(curr.spendCredits(request)) {
            logger.debug("Your balance is now " + curr.getBalance());
            repo.update(curr, curr.getName());
            return true;
        }
        return false;
    }

    /**
     * Takes credit from current account and sends it to another
     * @param username username of desired recipient
     * @param request amount you wish to send
     */
    public void gift(String username, int request) {
        if(curr.spendCredits(request)){
            Account temp = findAccount(username);
            if (temp!=null) {
                temp.addCredits(request);
                repo.update(temp, temp.getName());
                repo.update(curr, curr.getName());
            }
            else {
                curr.addCredits(request);
            }
        }
    }

}
