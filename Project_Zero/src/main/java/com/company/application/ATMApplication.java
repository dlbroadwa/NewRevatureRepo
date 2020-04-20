package com.company.application;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.LoginAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.consoleMenus.LoginScreen;
import com.company.consoleMenus.Screen;
import com.company.databaseUtils.PostgresqlConnection;
import com.company.loginAccounts.LoginAccount;

import java.util.Scanner;

/***
 * TODO write description for ATMApplication class
 *
 * @author Shawyn Kane
 */
public class ATMApplication {
    private Scanner scan = null;
    private PostgresqlConnection postgresqlConnection = new PostgresqlConnection();
    private LoginAccountDAO loginAccountDAO = new LoginAccountDAO(postgresqlConnection);
    private BankAccountDAO bankAccountDAO = new BankAccountDAO(postgresqlConnection);
    private UserNameBankAccountIDPairDAO userNameBankAccountIDPairDAO = new UserNameBankAccountIDPairDAO(postgresqlConnection);
    private LoginAccount credentialsEntered = null;
    private Screen currentScreen = new LoginScreen();

    /***
     * TODO write description for run method in ATMApplication class
     *
     * @author Shawyn Kane
     */
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
