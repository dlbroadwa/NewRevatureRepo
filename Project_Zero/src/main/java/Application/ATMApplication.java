package Application;

import Banking.Transaction;
import ConsoleMenus.LoginScreen;
import ConsoleMenus.Screen;
import DAO.BankAccountDAO;
import LoginAccounts.LoginAccount;

import java.util.Scanner;

public class ATMApplication {
    public final int NOACCOUNTID = -1;
    private Scanner scan = null;
    private LoginAccount loginAccount = new LoginAccount("John Smith", "12345", false);
    private BankAccountDAO bankAccountDAO = null;
    private LoginAccount credentialsEntered = null;
    private Screen currentScreen = new LoginScreen();
    private Transaction newTransaction = null;
    private int accountID = NOACCOUNTID;


    public ATMApplication() {
        bankAccountDAO = new BankAccountDAO();
        bankAccountDAO.retrieveAll();
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
}
