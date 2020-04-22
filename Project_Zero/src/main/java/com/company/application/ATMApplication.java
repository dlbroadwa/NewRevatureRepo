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
 * This class is where the application starts and helps pass the information needed around the application.
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
     * Starts the application and handles the swapping and running the screens.
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

    /***
     *
     * @return scan
     */
    public Scanner getScan() {
        return scan;
    }

    /***
     *
     * @return credentialsEntered
     */
    public LoginAccount getCredentialsEntered() {
        return credentialsEntered;
    }

    /***
     *
     * @param credentialsEntered
     */
    public void setCredentialsEntered(LoginAccount credentialsEntered) {
        this.credentialsEntered = credentialsEntered;
    }

    /***
     *
     * @return bankAccountDAO
     */
    public BankAccountDAO getBankAccountDAO() {
        return bankAccountDAO;
    }

    /***
     *
     * @return loginAccountDAO
     */
    public LoginAccountDAO getLoginAccountDAO() {
        return loginAccountDAO;
    }

    /***
     *
     * @return userNameBankAccountIDPairDAO
     */
    public UserNameBankAccountIDPairDAO getUserNameBankAccountIDPairDAO() {
        return userNameBankAccountIDPairDAO;
    }
}
