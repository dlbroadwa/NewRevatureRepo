package Application;

import ConsoleMenus.LoginScreen;
import ConsoleMenus.Screen;
import DAO.CustomerDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class ATMApplication {
    private Scanner scan = null;
    private Customer credentialsEntered = null;
    private Screen currentScreen = new LoginScreen();
    private CustomerDAO customerDAO = null;

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

    public Customer getCredentialsEntered() {
        return credentialsEntered;
    }

    public void setCredentialsEntered(Customer credentialsEntered) {
        this.credentialsEntered = credentialsEntered;
    }
}
