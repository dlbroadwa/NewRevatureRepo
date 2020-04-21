package com.game.service;

import com.game.data.MessageSQLRepo;
import com.game.data.Repository;
import com.game.models.Account;
import com.game.models.Message;
import java.util.ArrayList;
import java.util.List;

/**
 * Services both the account repo and the message repo
 * Possesses an account/message list for quicker access
 */
public class AccountService {
    List<Account> accountList;
    List<Message> messageList;
    Account curr;
    Repository<Account, String> repo;
    MessageSQLRepo mrepo;

    /**
     * Allows service to utilize the repos
     * @param repo account repo
     * @param mrepo message repo
     */
    public AccountService(Repository<Account, String> repo,Repository<Message, Integer> mrepo) {
        this.repo = repo;
        this.mrepo = (MessageSQLRepo) mrepo;
    }

    /**
     * Generates the account list
     */
    public void boot() {
        accountList = repo.findAll();
    }

    /**
     * Checks if there is another account with the given username
     * Helps prevent duplicate as the username field is unique
     * @param username username given
     * @return false if there is an existing account with that name
     */
    public boolean checkDuplicates(String username) {
        //checks if the name is already in list
        //returns true if it passes the check
        for (Account account : accountList) {
            if (username.equals(account.getName())) {
                System.out.println("Name has already been taken");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether credentials match an account and sets a reference to it
     * Generates a message list from the account information
     * @param username given username
     * @param password given password
     * @return true if account is found and sets it equal to curr
     */
    public boolean checkCredentials(String username, String password) {
        System.out.println(username+"\t"+password);
        for (Account account : accountList) {
            if (account.getName().equals(username)) {
                if (account.getPassword().equals(password)) {
                    curr=account;
                    mrepo.setName(curr.getName());
                    messageList=mrepo.findAll();
                    System.out.println("Welcome back " + curr.getName());
                    return true;
                } else {
                    System.out.println("Password does not match");
                    return false;
                }
            }
        }
        System.out.println("No such account found");
        return false;
    }

    /**
     * Creates account object with standard user level and adds it to the account
     * list and repo. Also sets up a new empty message list
     * created for the sign up process while setting up the curr account reference
     * @param username username of the new account
     * @param password password of the new account
     * @return true if the account has been created
     */
    public boolean signUp(String username, String password){
        if (checkDuplicates(username)) {
            curr = new Account(username, password);
            System.out.println("Welcome new user: " + username);
            accountList.add(curr);
            mrepo.setName(curr.getName());
            save(curr);
            //Creates an empty list so the program won't break when new user opens messages
            messageList = new ArrayList<>();
            return true;
        }
        return false;
    }

    /**
     * Creates a new account object and adds it to the list and repo
     * @param username username of new account
     * @param password password of newly created account
     * @param isadmin whether the account is admin level
     * @return true if account has been created successfully
     */
    public boolean createAccount(String username, String password, boolean isadmin) {
        if(checkDuplicates(username)) {
            Account temp = new Account(username, password, isadmin);
            accountList.add(temp);
            save(temp);
            return true;
        }
        return false;
    }

    /**
     * checks administer status of current account
     * @return true if current user is admin level
     */
    public boolean getIsAdminStatus(){
        return curr.isAdmin();
    }

    /**
     * removes the account from account list and call the repo's delete method to
     * remove the record from the database
     */
    public boolean deleteAccount(String username) {
        if (curr.getName().equals(username)) {
            System.out.println("If you want to delete your account," +
                    "please select \"close account\"");
            return false;
        }
        Account temp = findAccount(username);
        if (temp != null) {
            if (temp.isAdmin()){
                System.out.println("Cannot delete other admin accounts");
                return false;
            }
            String temp2 = temp.getName();
            accountList.remove(temp);
            repo.delete(temp2);
            System.out.println(temp2 + "'s account has been remove");
            return true;
        }
        return false;
    }

    /**
     * removes the account from account list and call the repo's delete method to
     * remove the record from the database
     */
    public void closeAccount() {
        String temp = curr.getName();
        accountList.remove(curr);
        repo.delete(temp);
        System.out.println("Your account has been remove");
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
        System.out.println("Account not found");
        return null;
    }

    /**
     * created mainly for testing purposes for access outside the class
     */
    public Account getCurr(){
        return curr;
    }

    /**
     * method to update account changes in the repo
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
            System.out.println(temp.getName()+"\t"+temp.getPassword()+"\t"+temp.getBalance()+
                    "\t"+(temp.isAdmin()?"Admin":"Player"));
        }
    }

    /**
     * get all account information (admin-accessible only)
     */
    public void list() {
        for (Account temp:accountList) {
            System.out.println(temp.getName()+"\t"+temp.getPassword()+"\t"+temp.getBalance()+
                    "\t"+(temp.isAdmin()?"Admin":"Player"));
        }
    }

    /**
     * deposit credits into current account
     * @param deposit credits you want to deposit
     */
    public void depositM(int deposit) {
        curr.addCredits(deposit);
        System.out.println("Your balance is now " + curr.getBalance());
        repo.update(curr);
    }

    /**
     * request credits from current account
     * and prints remaining balance
     * @param request amount you want to withdraw
     * @return true if the transaction is successful
     */
    public boolean spendC(int request) {
        if(curr.spendCredits(request)) {
            System.out.println("Your balance is now " + curr.getBalance());
            repo.update(curr);
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
                repo.update(temp);
                repo.update(curr);
            }
            else {
                curr.addCredits(request);
            }
        }
    }

    /**
     * prints out content of message to the user in a numbered format
     * Shows the username of who sent the message: message
     */
    public void readMessages() {
        int i=1;
        for (Message m:messageList) {
            System.out.println(i++ +": "+m.getFrom() + ": " + m.getMessage());
        }
    }

    /**
     * Creates and add message to the message list and call the repo's save method to
     * add the record to the database.
     */
    public void send(String to, String content) {
        //change here
        Account temp1 = findAccount(to);
        if (temp1==null){
            return;
        }
        Message temp = new Message(content,to,curr.getName(),0);
        mrepo.save(temp);
    }

    /**
     * removes the message from message list and call the repo's delete method to
     * remove the record from the database. Unlike the account's, the primary key
     * is used as the id to delete the record as it may be the only column with
     * unique values.
     * @param index message id
     */
    public void delete(int index) {
        --index;
        if (index < messageList.size()&&index>=0) {
            Message temp = messageList.remove(index);
            mrepo.delete(temp.getId());
        }
    }

    public void getMessageStatus(){
        System.out.println("You have "+messageList.size()+" messages");
    }

    /**
     * deletes by traversing through the user's messageList and getting their unique ids from the object
     */
    public void deleteAll() {
        for (int i = 0; i<messageList.size(); i++) {
            delete(i);
        }
    }

    /**
     * prints out the current user's username, password, balance, and account type
     */
    public void getAccountInfo() {
        System.out.println(curr.getName()+"\t"+curr.getPassword()+"\t"+curr.getBalance()+
                "\t"+(curr.isAdmin()?"Admin":"Player"));
    }

    /**
     * Changes current user's password
     * @param newPassword desired password
     */
    public void changePassword(String newPassword){
        curr.setPassword(newPassword);
        repo.update(curr);
    }
}
