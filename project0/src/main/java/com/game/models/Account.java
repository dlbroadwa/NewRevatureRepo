package com.game.models;

import java.util.logging.Logger;

/**
 * Class that represents a player account with 4 main attributes:
 * name, password, balance(represents in-game credits), and isAdmin.
 * Included getters and setters as well as additional behaviour
 * in which the balance is modified
 */
public class Account {
    private final String name;
    private String password;
    private	int balance;
    private final boolean isAdmin;

    /**
     * Creates standard player account object
     * @param name username of the player
     * @param password password of the player
     */
    public Account(String name, String password){
        this.name = name;
        this.password = password;
        this.isAdmin=false;
    }

    /**
     * Could create either an admin or a standard player
     * @param name username of the user
     * @param password password of the user
     * @param isAdmin determines whether the new account would be admin or not
     */
    public Account(String name, String password, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Account(String name, String password, boolean isAdmin, int deposit){
        this.name = name;
        this.password = password;
        this.balance = deposit;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Just adds input to the user's balance
     * @param deposit amount of credits going into the account
     */
    public void addCredits(int deposit){
        if (deposit<=0){
            Logger.getLogger("Cannot deposit less than or equal to 0");
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
    public boolean spendCredits(int request){
        if (balance>=request) {
            balance -= request;
            return true;
        }else{
            Logger.getLogger("Not enough funds");
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
}
