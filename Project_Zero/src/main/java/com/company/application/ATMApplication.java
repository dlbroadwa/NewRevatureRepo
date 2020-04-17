package com.company.application;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.LoginAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.consoleMenus.LoginScreen;
import com.company.consoleMenus.Screen;
import com.company.databaseUtils.PostgresqlConnection;
import com.company.loginAccounts.LoginAccount;

import java.util.Scanner;

public class ATMApplication {
    private Scanner scan = null;
    private LoginAccountDAO loginAccountDAO = new LoginAccountDAO(new PostgresqlConnection("jdbc:postgresql://" + System.getenv("POSTGRES_URL") + ":" + System.getenv("POSTGRES_PORT") + "/" + System.getenv("POSTGRES_DATABASE_NAME"), System.getenv("POSTGRES_USERNAME"), System.getenv("POSTGRES_PASSWORD"), System.getenv("POSTGRES_DEFAULT_SCHEMA")));
    private BankAccountDAO bankAccountDAO = new BankAccountDAO(new PostgresqlConnection("jdbc:postgresql://" + System.getenv("POSTGRES_URL") + ":" + System.getenv("POSTGRES_PORT") + "/" + System.getenv("POSTGRES_DATABASE_NAME"), System.getenv("POSTGRES_USERNAME"), System.getenv("POSTGRES_PASSWORD"), System.getenv("POSTGRES_DEFAULT_SCHEMA")));
    private UserNameBankAccountIDPairDAO userNameBankAccountIDPairDAO = new UserNameBankAccountIDPairDAO(new PostgresqlConnection("jdbc:postgresql://" + System.getenv("POSTGRES_URL") + ":" + System.getenv("POSTGRES_PORT") + "/" + System.getenv("POSTGRES_DATABASE_NAME"), System.getenv("POSTGRES_USERNAME"), System.getenv("POSTGRES_PASSWORD"), System.getenv("POSTGRES_DEFAULT_SCHEMA")));
    private LoginAccount credentialsEntered = null;
    private Screen currentScreen = new LoginScreen();

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

    public BankAccountDAO getBankAccountDAO() {
        return bankAccountDAO;
    }

    public LoginAccountDAO getLoginAccountDAO() {
        return loginAccountDAO;
    }

    public UserNameBankAccountIDPairDAO getUserNameBankAccountIDPairDAO() {
        return userNameBankAccountIDPairDAO;
    }
}
