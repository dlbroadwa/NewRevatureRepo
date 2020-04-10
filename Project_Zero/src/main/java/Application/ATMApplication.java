package Application;

import ConsoleMenus.LoginScreen;
import ConsoleMenus.Screen;
import LoginAccounts.LoginAccount;

import java.util.Scanner;

public class ATMApplication {
    private Scanner scan = null;
    private LoginAccount credentialsEntered = null;
    private Screen currentScreen = new LoginScreen();
    private LoginAccount loginAccount = new LoginAccount("John Smith", "12345");

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
