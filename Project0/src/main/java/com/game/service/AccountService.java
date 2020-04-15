package com.game.service;

import com.game.data.Repository;
import com.game.models.Account;

import java.util.ArrayList;

public class AccountService {
    ArrayList<Account> accountList;
    Repository repo;
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
        for (int i = 0; i<accountList.size(); i++){
            if (accountList.get(i).getName().equals(username)){
                if (accountList.get(i).getPassword().equals(password)){
                    System.out.println("Welcome back "+accountList.get(i).getName());
                    return true;
                }
                else {
                    System.out.println("Password does not match");
                    return false;
                }
            }
        }
        System.out.println("No such account found");
        return false;
    }

    public void update(Account account, String username) {
    }

    public void createAccount(String choiceText, String choiceText2) {
    }

    public void deleteAccount(int choice) {
    }

    public boolean getAccountInfo(int choice) {
        return false;
    }

    public void list() {
    }

    public void readMessages() {
    }

    public void depositM(int choice, int choice2) {
    }

    public void spendC(int choice, int choice2) {
    }

    public void send(String choiceText, String choiceText2) {
    }

    public void createAccount(String choiceText, String choiceText2, boolean b) {
    }
}
