package UI;

import gameaccounts.ListManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuSelect {
    private Menu menu;
    private Scanner in;
    private ListManager accounts;
    private int choice;
    private int choice2;
    private String choiceText;
    private String choiceText2;
    public boolean exitCondition = false;
    public boolean exitCondition2 = true;

    public MenuSelect(){
        in = new Scanner(System.in);
        menu = new Menu();
        //admin menu
        accounts = new ListManager();
        accounts.boot();
    }

    public void entryPage(){
        menu.entryText();
        try {
            choice = in.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("that is not a number.");
            in.next();
            System.out.println("Press Enter to continue");
            in.nextLine();
            return;
        }

        switch (choice){
            case 1:
                logIn();
                break;
            case 2:
                signUp();
                break;
            default:
                exitCondition = true;
                System.out.println("Goodbye");
        }

        return;
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
            in.nextLine();
            return;
        }
        //choiceText = in.nextLine();
        switch(choice) {
            case 1:
                System.out.println("Enter the username of the account");
                do {
                    choiceText = in.nextLine();
                }while(!accounts.checkDuplicates(choiceText));

                System.out.println("Enter Password of the account");
                choiceText2= in.nextLine();
                accounts.createAccount(choiceText,choiceText2);
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
                //accounts.updateAccount();
                //no longer used as it automatically updatess
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
                System.out.println("What is the ID you wish to withdraw from?");
                choice=in.nextInt();
                System.out.println("How many diamonds would you like to withdraw?");
                choice2=in.nextInt();
                accounts.spendC(choice, choice2);
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
                System.out.println(accounts.getAccountInfo(choice));
                in.nextLine();
                break;
            case 7:
                accounts.list();
                in.nextLine();
                break;
            default:
                //exit program
                exitCondition2 = true;
                System.out.println("Signing off");
                break;
        }
        System.out.println("Press Enter to continue");
        choiceText = in.nextLine();
        return;
    }

    public void logIn(){
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
        if(accounts.checkCredentials(username, password)){
            exitCondition2=false;
        }
        return;
    }

    public void signUp(){
        String username = null;
        String password = null;
        do {
            System.out.println("What user name would you like?");
            username = in.nextLine();
        }while(accounts.checkDuplicates(username));
        System.out.println("What password would you like?");
        password=in.nextLine();
        accounts.createAccount(username,password);
        exitCondition2=false;
        return;
    }
}
