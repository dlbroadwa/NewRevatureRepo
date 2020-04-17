package com.game.service;

import com.game.data.Repository;
import com.game.models.Account;
import java.util.List;

public class AccountService {
    List<Account> accountList;
    Account curr;
    Repository<Account, String> repo;
    public AccountService(Repository<Account, String> repo) {
        this.repo = repo;
    }

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

    public boolean checkCredentials(String username, String password) {
        System.out.println(username+"\t"+password);
        for (Account account : accountList) {
            if (account.getName().equals(username)) {
                if (account.getPassword().equals(password)) {
                    curr=account;
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

    public void signUp(String username, String password){
        curr = new Account(username, password);
        System.out.println("Welcome new user: "+username);
        accountList.add(curr);
    }

    public void save(Account account) {
        repo.save(account);
    }

    public void getAccountInfo(String username) {
        Account temp = findAccount(username);
        if (temp!=null) {
            System.out.println(temp.getName()+"\t"+temp.getPassword()+"\t"+temp.getBalance()+
                    "\t"+(temp.isAdmin()?"Admin":"Player"));
        }
    }

    public void list() {
        for (Account temp:accountList) {
            System.out.println(temp.getName()+"\t"+temp.getPassword()+"\t"+temp.getBalance()+
                    "\t"+(temp.isAdmin()?"Admin":"Player"));
        }
    }

    public void readMessages() {
    }

    public void depositM(int deposit) {
        curr.addCredits(deposit);
        System.out.println("Your balance is now" + curr.getBalance());
    }

    public void spendC(int request) {
        curr.spendCredits(request);
        System.out.println("Your balance is now" + curr.getBalance());
    }

    public void send(String choiceText, String choiceText2) {
    }

    public void createAccount(String username, String password, boolean isadmin) {
        Account temp = new Account(username, password, isadmin);
        accountList.add(temp);
        save(temp);
    }

    public void createAccount(String username, String password) {
        Account temp = new Account(username, password);
        accountList.add(temp);
        save(temp);
    }

    public void deleteAccount(String username) {
        if (curr.getName()==username){
            System.out.println("If you want to delete your account," +
                    "please select \"close account\"");
            return;
        }
        Account temp = findAccount(username);
        String temp2 = temp.getName();
        accountList.remove(temp);
        repo.delete(temp2);
        System.out.println(temp2+"'s account has been remove");
    }

    public void closeAccount() {
        String temp = curr.getName();
        accountList.remove(curr);
        repo.delete(temp);
        System.out.println("Your account has been remove");
    }

    public void boot() {
        accountList = repo.findAll();
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

}
