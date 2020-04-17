package com.game.service;

import com.game.data.MessageSQLRepo;
import com.game.data.Repository;
import com.game.models.Account;
import com.game.models.Message;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    List<Account> accountList;
    List<Message> messageList;
    Account curr;
    Repository<Account, String> repo;
    MessageSQLRepo mrepo;

    public AccountService(Repository<Account, String> repo,Repository<Message, Integer> mrepo) {
        this.repo = repo;
        this.mrepo = (MessageSQLRepo) mrepo;
    }

    public void boot() {
        accountList = repo.findAll();
    }

    //returns true if there is an existing account with that name
    public boolean checkDuplicates(String username) {
        //checks if the name is already in list
        //returns true if it passes the check
        for (Account account : accountList) {
            if (username.equals(account.getName())) {
                System.out.println("Name has already been taken");
                return true;
            }
        }
        return false;
    }

    //returns true if account is found and sets it equal to curr
    public boolean checkCredentials(String username, String password) {
        System.out.println(username+"\t"+password);
        for (Account account : accountList) {
            if (account.getName().equals(username)) {
                if (account.getPassword().equals(password)) {
                    curr=account;
                    mrepo.setName(curr.getName());
                    messageList=mrepo.findAll();
                    System.out.println("Welcome back " + account.getName());
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

    //created for the sign up process while setting up the curr account reference
    public void signUp(String username, String password){
        curr = new Account(username, password);
        System.out.println("Welcome new user: "+username);
        accountList.add(curr);
        mrepo.setName(curr.getName());
        save(curr);
        //Creates an empty list so the program won't break when new user opens messages
        messageList = new ArrayList<>();
    }

    public void createAccount(String username, String password, boolean isadmin) {
        Account temp = new Account(username, password, isadmin);
        accountList.add(temp);
        save(temp);
    }

    public boolean getIsAdminStatus(){
        return curr.isAdmin();
    }

    public void deleteAccount(String username) {
        if (curr.getName().equals(username)) {
            System.out.println("If you want to delete your account," +
                    "please select \"close account\"");
            return;
        }
        Account temp = findAccount(username);
        if (temp != null) {
            String temp2 = temp.getName();
            accountList.remove(temp);
            repo.delete(temp2);
            System.out.println(temp2 + "'s account has been remove");
        }
    }

    public void closeAccount() {
        String temp = curr.getName();
        accountList.remove(curr);
        repo.delete(temp);
        System.out.println("Your account has been remove");
    }

    private Account findAccount(String username){
        for (Account temp:accountList) {
            if (temp.getName().equals(username)){
                return temp;
            }
        }
        System.out.println("Account not found");
        return null;
    }

    //method to update account changes in the repo
    public void save(Account account) {
        repo.save(account);
    }

    //get current account information
    public void getAccountInfo(String username) {
        Account temp = findAccount(username);
        if (temp!=null) {
            System.out.println(temp.getName()+"\t"+temp.getPassword()+"\t"+temp.getBalance()+
                    "\t"+(temp.isAdmin()?"Admin":"Player"));
        }
    }

    //get all account information (admin-accessible only)
    public void list() {
        for (Account temp:accountList) {
            System.out.println(temp.getName()+"\t"+temp.getPassword()+"\t"+temp.getBalance()+
                    "\t"+(temp.isAdmin()?"Admin":"Player"));
        }
    }

    //deposit credits into current account
    public void depositM(int deposit) {
        curr.addCredits(deposit);
        System.out.println("Your balance is now" + curr.getBalance());
    }

    //request credits from current account
    public void spendC(int request) {
        curr.spendCredits(request);
        System.out.println("Your balance is now" + curr.getBalance());
    }

    public void gift(String username, int choice) {
        if(curr.spendCredits(choice)){
            Account temp = findAccount(username);
            if (temp!=null) {
                temp.addCredits(choice);
            }
        }
    }

    //this section access the message service
    // prints out content of message to the user in a numbered format
    public void readMessages() {
        int i=1;
        for (Message m:messageList) {
            System.out.println(i++ +": "+m.getFrom() + ": " + m.getMessage());
        }
    }

    //creates a new message
    public void send(String to, String content) {
        //change here
        Message temp = new Message(content,to,curr.getName(),0);
        mrepo.save(temp);
        temp.setId(mrepo.getLastId());
        messageList.add(temp);
    }

    //deletes message by index and updates repo
    public void delete(int index){
        Message temp = messageList.remove(index);
        mrepo.delete(temp.getId());
    }

    public void getMessageStatus(){
        System.out.println("You have "+messageList.size()+" messages");
    }

    //deletes by traversing through the user's messageList and getting their unique ids from the object
    public void deleteAll() {
        for (Message m:messageList) {
            delete(m.getId());
        }
    }

}
