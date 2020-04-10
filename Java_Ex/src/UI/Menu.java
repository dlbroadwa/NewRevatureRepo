package UI;

import java.io.FileReader;
import java.util.Scanner;

public class Menu {
    //shows different menu depending on whether it is a user or administrator
    public Menu(){
        //administrative access
    }

    public Menu(int uid){
        //user id
    }

    public static void textOptions(String name,boolean x) {
        if (x) {
            System.out.println("Hello Administrator");
            System.out.println("1: Create Account");
            System.out.println("2: Delete Account");
            System.out.println("3: Save account list status");
            System.out.println("4: Deposit credit into account");
            System.out.println("5: Spend credits from account");
            System.out.println("6: Show Account Info");
            System.out.println("7: List all accounts");
            System.out.println("8: Log out");
        } else {
            System.out.println("Hello User " + name);
            System.out.println("1: Close Account Permanently");
            System.out.println("2: Save account list status");
            System.out.println("3: Deposit credit into account");
            System.out.println("4: Spend credits from account");
            System.out.println("5: Show Account Info");
            System.out.println("6: List all accounts");
            System.out.println("7: Log out");
        }
    }
}
