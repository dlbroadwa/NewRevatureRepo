package UI;

import gameaccounts.ListManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CredentialsCheck {
    public int logIntext(Scanner in){
        int index = 0;
        String username = null;
        String password = null;
        System.out.println("Username:");

        try {
            username = in.nextLine();
        }
        catch (InputMismatchException e){
            e.printStackTrace();
        }

        System.out.println("Password:");

        try {
            password = in.nextLine();
        }
        catch (InputMismatchException e){
            e.printStackTrace();
        }

        ListManager.checkCredentials(username, password);

        return index;
    }
}
