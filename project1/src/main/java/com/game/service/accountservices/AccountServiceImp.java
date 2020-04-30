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
     * checks administer status of current account
     * @return true if current user is admin level
     */
    public boolean getIsAdminStatus(){
        return curr.getIsAdmin();
    }

    /**
     * removes the account from account list and call the repo's delete method to
     * remove the record from the database
     */
    public boolean deleteAccount(String username) {
        if (curr.getName().equals(username)) {
            logger.debug("If you want to delete your account," +
                    "please select \"close account\"");
            return false;
        }
        Account temp = findAccount(username);
        if (temp != null) {
            if (temp.getIsAdmin()){
                logger.debug("Cannot delete other admin accounts");
                return false;
            }
            String temp2 = temp.getName();
            accountList.remove(temp);
            repo.delete(temp2);
            logger.debug(temp2 + "'s account has been remove");
            return true;
        }
        return false;
    }

    /**
     * call the deleteAccount method on a different thread
     * @param username username to be passed
     */
    public void deleteAccountThread(String username){
        Thread d = new Thread(() -> deleteAccount(username));
        d.start();
    }

    public void createAccountThread(String username, String password, boolean isAdmin){
        Thread c = new Thread(() -> createAccount(username,password,isAdmin));
        c.start();
    }

    /**
     * removes the account from account list and call the repo's delete method to
     * remove the record from the database. Also deletes all messages to the user.
     */
    public void closeAccount() {
        String temp = curr.getName();
        accountList.remove(curr);
        repo.delete(temp);
        logger.debug("Your account has been remove");
    }

    /**
     * Finds reference to account by traversing the account list
     * and matching the username
     * @param username requested account's username
     * @return account object requested
     */
    private Account findAccount(String username){
        for (Account temp:accountList) {
            if (temp.getName().equals(username)){
                return temp;
            }
        }
        logger.debug("Account not found");
        return null;
    }

    /**
     * created mainly for testing purposes for access outside the class
     */
    public Account getCurr(){
        return curr;
    }

    /**
     * method to save new account in the repo
     * Called when account state changes
     * @param account changed account
     */
    public void save(Account account) {
        repo.save(account);
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
     * get all account information (admin-accessible only)
     */
    public void list() {
        for (Account temp:accountList) {
            logger.debug(temp.getName()+"\t"+temp.getPassword()+"\t"+temp.getBalance()+
                    "\t"+(temp.getIsAdmin()?"Admin":"Player"));
        }
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
