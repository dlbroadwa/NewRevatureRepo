package UI;

import gameaccounts.Account;
import gameaccounts.ListManager;
import utils.ConnectionUtils;
import utils.PostgresConnectionUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuSelect {
    private Menu menu;
    public Scanner in;
    public ListManager accounts;
    public boolean exitCondition = false;
    public boolean exitCondition2 = true;
    private Account curr;

    public MenuSelect(){
        in = new Scanner(System.in);
        menu = new Menu();
        //admin menu
        accounts = new ListManager();
        accounts.boot();
    }

    public void entryPage(){
        int choice;
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
        String username = null;
        String password = null;
        switch (choice){
            case 1:
                System.out.println("Username:");
                in.nextLine();
                username = in.nextLine();
                System.out.println("Password:");
                password = in.nextLine();
                logIn(username,password);
                break;
            case 2:
                System.out.println("Username:");
                in.nextLine();
                username = in.nextLine();
                System.out.println("Password:");
                password = in.nextLine();
                signUp(username,password);
                break;
            default:
                exitCondition = true;
                System.out.println("Goodbye");
        }

    }

    //overload choiceSelection so that users are led to different cases than administers
    public void choiceSelection() {
        int choice;
        menu.textOptions(curr.getName(),curr.getIsAdmin());
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
        String choiceText;
        switch(choice) {
            case 1:
                System.out.println("Enter the username of the account");
                in.nextLine();
                do {
                    choiceText = in.nextLine();
                }while(accounts.checkDuplicates(choiceText));

                System.out.println("Enter Password of the account");
                String choiceText2 = in.nextLine();
                accounts.createAccount(choiceText, choiceText2);
                //create account
                break;
            case 2:
                System.out.println("What is the index of the account you wish to delete?");
                choice=in.nextInt();
                accounts.deleteAccount(choice);
                //delete account
                break;
            case 3:
                //create admin account
                System.out.println("Enter the username of the admin account");
                in.nextLine();
                do {
                    choiceText = in.nextLine();
                }while(accounts.checkDuplicates(choiceText));

                System.out.println("Enter Password of the admin account");
                choiceText2 = in.nextLine();
                accounts.createAccount(choiceText, choiceText2, true);
                break;
            case 4:
                //deposit credits into account
                System.out.println("What is the account Id that you would like to deposit into?");
                choice=in.nextInt();
                System.out.println("How many diamonds do you like to deposit");
                int choice2 = in.nextInt();
                accounts.depositM(choice, choice2);
                break;
            case 5:
                System.out.println("What is the ID you wish to withdraw from?");
                choice=in.nextInt();
                System.out.println("How many diamonds would you like to withdraw?");
                choice2 =in.nextInt();
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
                break;
            case 7:
                accounts.list();
                break;
            case 8:
                accounts.readMessages();
                break;
            case 9:
                System.out.println("Who do you want to send a message to?");
                choiceText=in.nextLine();
                in.nextLine();
                System.out.println("What is you're message?");
                choiceText2=in.nextLine();
                accounts.send(choiceText,choiceText2);
                break;
            default:
                //exit program
                exitCondition2 = true;
                System.out.println("Signing off");
                break;
        }
        in.nextLine();
        System.out.println("Press Enter to continue");
        in.nextLine();
    }

    public void logIn(String username, String password){
        if(accounts.checkCredentials(username, password)){
            exitCondition2=false;
            curr = accounts.getCurr();
        }
    }

    public void signUp(String username, String password){
        if(accounts.checkDuplicates(username)){
            return;
        }
        curr=accounts.signUp(username,password);
        exitCondition2=false;
    }
}
