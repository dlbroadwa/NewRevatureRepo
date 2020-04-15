package com.game.screens;
import com.game.app.Application;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        int choice;
        menuText();
        Scanner in = app.getScanner();
        try {
            choice = in.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("that is not a number.");
            in.next();
            System.out.println("Press Enter to continue");
            in.nextLine();
            return this;
        }
        //choiceText = in.nextLine();
        String choiceText;
        switch(choice) {
            case 1:
                System.out.println("Enter the username of the account");
                in.nextLine();
                do {
                    choiceText = in.nextLine();
                }while(app.getAccountService().checkDuplicates(choiceText));

                System.out.println("Enter Password of the account");
                String choiceText2 = in.nextLine();
                app.getAccountService().createAccount(choiceText, choiceText2);
                //create account
                break;
            case 2:
                System.out.println("What is the index of the account you wish to delete?");
                choice=in.nextInt();
                app.getAccountService().deleteAccount(choice);
                //delete account
                break;
            case 3:
                //create admin account
                System.out.println("Enter the username of the admin account");
                in.nextLine();
                do {
                    choiceText = in.nextLine();
                }while(app.getAccountService().checkDuplicates(choiceText));

                System.out.println("Enter Password of the admin account");
                choiceText2 = in.nextLine();
                app.getAccountService().createAccount(choiceText, choiceText2, true);
                break;
            case 4:
                //deposit credits into account
                System.out.println("What is the account Id that you would like to deposit into?");
                choice=in.nextInt();
                System.out.println("How many diamonds do you like to deposit");
                int choice2 = in.nextInt();
                app.getAccountService().depositM(choice, choice2);
                break;
            case 5:
                System.out.println("What is the ID you wish to withdraw from?");
                choice=in.nextInt();
                System.out.println("How many diamonds would you like to withdraw?");
                choice2 =in.nextInt();
                app.getAccountService().spendC(choice, choice2);
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
                System.out.println(app.getAccountService().getAccountInfo(choice));
                break;
            case 7:
                app.getAccountService().list();
                break;
            case 8:
                app.getAccountService().readMessages();
                break;
            case 9:
                System.out.println("Who do you want to send a message to?");
                choiceText=in.nextLine();
                in.nextLine();
                System.out.println("What is you're message?");
                choiceText2=in.nextLine();
                app.getAccountService().send(choiceText,choiceText2);
                break;
            default:
                //exit program
                System.out.println("Signing off");
                return new EntryScreen();
        }
        in.nextLine();
        System.out.println("Press Enter to continue");
        in.nextLine();

        return null;
    }

    private void menuText(){
        System.out.println("Hello Administrator");
        System.out.println("1: Create Account");
        System.out.println("2: Delete Account");
        System.out.println("3: Create admin account");
        System.out.println("4: Deposit credit into account");
        System.out.println("5: Spend credits from account");
        System.out.println("6: Show Account Info");
        System.out.println("7: List all accounts");
        System.out.println("8: Read all messages");
        System.out.println("9: Send a message");
        System.out.println("default: Log out");
    }
}
