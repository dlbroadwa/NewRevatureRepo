package BankApp.app;

import java.text.DecimalFormat;
import java.util.Scanner;

/************************************************
 *
 * Class for main menu functionality after user
 * logs in successfully
 *
 * Methods:
 * runMenu - will run the main menu after login
 *
 ***********************************************/
public class MainMenu extends Functions{

    //declare variables
    Scanner in = new Scanner(System.in);
    int userChoice;
    boolean quit = false;
    DecimalFormat df = new DecimalFormat("###.##");
    float balance = 0f;
    float amount = 0f;


    //method to run main menu
    public void runMenu(){

        //do while will run as long as user does not press 0 to quit
        do {

            System.out.println("1. Deposit money");
            System.out.println("2. Withdraw money");
            System.out.println("3. Check balance");
            System.out.print("Your choice, 0 to quit: ");
            userChoice = in.nextInt();

            switch (userChoice) {
                case 1:
                    // deposit money
                    deposit();
                    break;
                case 2:
                    // withdraw money
                    withdraw();
                    break;
                case 3:
                    // check balance
                    checkBalance();
                    break;
                case 0:
                    //quit program
                    quit = true;
                    break;

                default:
                    System.out.println("\n" + "Wrong choice." );
                    break;
            }
            System.out.println();
        } while (!quit);
        System.out.println("\n" + "Thanks for using our bank!");
    }



}