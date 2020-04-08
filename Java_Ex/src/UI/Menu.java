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

    public static void textOptions() {
        // list options and clear out console
        System.out.println("Hello Administrator");
        System.out.println("1: Create Account");
        System.out.println("2: Delete Account");
        System.out.println("3: Save account list status");
        System.out.println("4: Deposit credit into account");
        System.out.println("5: Spend credits from account");
        System.out.println("6: Show Account Info");
        System.out.println("7: List all accounts");
        System.out.println("default: Exit");
    }

}
