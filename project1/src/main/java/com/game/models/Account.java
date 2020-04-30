package com.game.models;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a player account with 4 main attributes:
 * name, password, balance(represents in-game credits), and isAdmin.
 * Included getters and setters as well as additional behaviour
 * in which the balance is modified
 */
public class Account {
    private final String username;
    private String password;
    private final String email;
    private List<String> friends;
    private String bankAccount;
    private	int balance;
    static final Logger logger = Logger.getLogger(Account.class);

    public Account(String username, String password, String email, String friends, int balance) {
        this.friends = new ArrayList<>();
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.email = email;
        if (friends.equals("")){
            return;
        }
        String[] temp = friends.split(",");
        this.friends.addAll(Arrays.asList(temp));
    }

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        friends = new ArrayList<>();
    }

    public String getName() {
        return username;
    }

    public int getBalance() {
        return balance;
    }

    /**
     * Just adds input to the user's balance
     * @param deposit amount of credits going into the account
     */
    public void addC(int deposit){
        if (deposit<=0){
            logger.debug("Cannot deposit less than or equal to 0");
            return;
        }
        balance+=deposit;
    }

    /**
     * Used to take out credits
     * Checks if they have enough credits to withdraw from and decrements
     * the user's balance
     * @param request Amount of credit the user is requesting
     * @return true if they have enough
     */
    public boolean spendC(int request){
        if (balance>=request) {
            balance -= request;
            return true;
        }else{
            logger.debug("Not enough funds");
            return false;
        }
    }

    /**
     * Allows user to change password
     * @param password password
     */
    public void setPassword(String password) {
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setBankAccount(String bankAccount){
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public List<String> getFriends() {
        return friends;
    }
}
