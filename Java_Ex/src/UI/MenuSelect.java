package UI;

import gameaccounts.ListManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuSelect {
    private Scanner in;
    private ListManager accounts;
    private int choice;
    private int choice2;
    private String choiceText;
    public boolean exitCondition = false;

    public MenuSelect(int size){
        in = new Scanner(System.in);
        //admin menu
        accounts = new ListManager(size);
    }

    //overload choiceSelection so that users are led to different cases than administers
    public void choiceSelection() {
        try {
            choice = in.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("that is not a number.");
            in.next();
            System.out.println("Press Enter to continue");
            choiceText = in.nextLine();
            return;
        }
        choiceText = in.nextLine();
        switch(choice) {
            case 1:
                System.out.println("Enter the username of the account");
                do {
                    choiceText = in.nextLine();
                }while(!accounts.checkDuplicates(choiceText));
                System.out.println("Enter desired initial credits you wish to deposit");
                choice= in.nextInt();
                accounts.createAccount(choiceText,choice);
                //create account
                break;
            case 2:
                System.out.println("What is the index of the account you wish to delete?");
                choice=in.nextInt();
                accounts.deleteAccount(choice);
                //delete account
                break;
            case 3:
                //save account list status
                accounts.saveList();
                break;
            case 4:
                //deposit credits into account
                System.out.println("What is the account Id that you would like to deposit into?");
                choice=in.nextInt();
                System.out.println("How many diamonds do you like to deposit");
                choice2=in.nextInt();
                accounts.depositM(choice, choice2);
                break;
            case 5:
                //spend credits (will remove from administrator menu and place sole into user's
                System.out.println("How many diamonds would you like to withdraw?");
                break;
            case 6:
                //lookup account information
                System.out.println("What is the account you wish to look up?");
                try {
                    choice = in.nextInt();
                }
                catch (InputMismatchException e){
                    System.out.println("Invalid Entry");
                    in.nextLine();
                }
                accounts.getAccountInfo(choice);
                in.nextLine();
                break;
            default:
                //exit program
                //saves on exit
                accounts.saveList();
                exitCondition = true;
                System.out.println("Goodbye");
                break;
        }
        System.out.println("Press Enter to continue");
        choiceText = in.nextLine();
        return;
    }
}
