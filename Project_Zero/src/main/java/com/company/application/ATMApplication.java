package com.company.application;

import com.company.banking.Account;
import com.company.banking.Transaction;
import com.company.consoleMenus.LoginScreen;
import com.company.consoleMenus.Screen;
import com.company.DAO.BankAccountDAO;
import com.company.DAO.LoginAccountDAO;
import com.company.loginAccounts.LoginAccount;
import com.company.databaseUtils.PostgresqlConnection;

import java.util.ArrayList;
import java.util.Scanner;

public class ATMApplication {
    public final int NOACCOUNTID = -1;
    private Scanner scan = null;
    private LoginAccount loginAccount = new LoginAccount("John Smith", "12345", false);
    private LoginAccountDAO loginAccountDAO = new LoginAccountDAO();
    private BankAccountDAO bankAccountDAO = null;
    private LoginAccount credentialsEntered = null;
    private Screen currentScreen = new LoginScreen();
    private Transaction newTransaction = null;
    private int accountID = NOACCOUNTID;


    public ATMApplication() {
        bankAccountDAO = new BankAccountDAO(new PostgresqlConnection("jdbc:postgresql://postgres.cls1tahxfwjt.us-east-2.rds.amazonaws.com:5432/postgres","atm_user", "dontbother!135", "public"));
        ArrayList<Account> accounts = bankAccountDAO.retrieveAll();
        for (int i = 0; i < accounts.size(); i++) {
            accounts.get(i).printToScreen();
        }
        // TODO load in accounts
        // TODO Load in accounts
    }

    public void run() {
        try {

            scan = new Scanner(System.in);

            while (currentScreen != null) currentScreen = currentScreen.run(this);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }

    public Scanner getScan() {
        return scan;
    }

    public LoginAccount getCredentialsEntered() {
        return credentialsEntered;
    }

    public void setCredentialsEntered(LoginAccount credentialsEntered) {
        this.credentialsEntered = credentialsEntered;
    }

    public LoginAccount getLoginAccount() {
        return loginAccount;
    }

    public BankAccountDAO getBankAccountDAO() {
        return bankAccountDAO;
    }

    public LoginAccountDAO getLoginAccountDAO() {
        return loginAccountDAO;
    }
}
