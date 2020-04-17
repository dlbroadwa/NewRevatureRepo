package com.game.screens;
import com.game.app.Application;
import com.game.app.GameAccountApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuScreen implements Screen {
    GameAccountApplication app;
    boolean isAdmin;

    @Override
    public Screen doScreen(Application ap) {
        app = (GameAccountApplication) ap;
        isAdmin = app.getAccountService().getIsAdminStatus();
        int choice;
        String choiceText;
        String choiceText2;
        menuText();
        Scanner in = app.getScanner();
        try {
            choice = in.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("that is not a number.");
            in.next();
            return this;
        }
        if(isAdmin) {
            switch (choice) {
                case 1:
                    System.out.println("What is the index of the account you wish to delete?");
                    choiceText = in.nextLine();
                    app.getAccountService().deleteAccount(choiceText);
                    //delete account
                    break;
                case 2:
                    //create admin account
                    System.out.println("Enter the username of the admin account");
                    in.nextLine();
                    do {
                        choiceText = in.nextLine();
                    } while (app.getAccountService().checkDuplicates(choiceText));

                    System.out.println("Enter Password of the admin account");
                    choiceText2 = in.nextLine();
                    app.getAccountService().createAccount(choiceText, choiceText2, true);
                    break;
                case 3:
                    //deposit credits into account
                    System.out.println("How many diamonds do you like to deposit");
                    try {
                        choice = in.nextInt();
                    }
                    catch (InputMismatchException e){
                        System.out.println("that is not a number.");
                        in.next();
                        return this;
                    }
                    app.getAccountService().depositM(choice);
                    break;
                case 4:
                    System.out.println("How many diamonds would you like to withdraw?");
                    try {
                        choice = in.nextInt();
                    }
                    catch (InputMismatchException e){
                        System.out.println("that is not a number.");
                        in.next();
                        return this;
                    }
                    app.getAccountService().spendC(choice);
                    break;
                case 5:
                    System.out.println("Whose account do you wish to send diamonds to?");
                    choiceText = in.nextLine();
                    System.out.println("How much do you want to send them");
                    try {
                        choice = in.nextInt();
                    }
                    catch (InputMismatchException e){
                        System.out.println("that is not a number.");
                        in.next();
                        return this;
                    }
                    app.getAccountService().gift(choiceText,choice);
                    break;
                case 6:
                    //lookup account information
                    System.out.println("Whose account do you wish to look up?");
                    choiceText = in.nextLine();
                    app.getAccountService().getAccountInfo(choiceText);
                    break;
                case 7:
                    app.getAccountService().list();
                    break;
                case 8:
                    return new MessageScreen();
                case 9:
                    System.out.println("Are you sure you want to close your account? Enter yes" +
                            " if you are certain");
                    choiceText = in.nextLine();
                    in.nextLine();
                    if (choiceText.equals("yes")) {
                        app.getAccountService().closeAccount();
                    }
                default:
                    //exit program
                    System.out.println("Signing off");
                    return new EntryScreen();
            }
        }else{
            switch(choice) {
                case 1:
                    System.out.println("How many diamonds would you like to withdraw?");
                    try {
                        choice = in.nextInt();
                    }
                    catch (InputMismatchException e){
                        System.out.println("that is not a number.");
                        in.next();
                        return this;
                    }
                    app.getAccountService().spendC(choice);
                    break;
                case 2:
                    System.out.println("Whose account do you wish to send diamonds to?");
                    choiceText = in.nextLine();
                    System.out.println("How much do you want to send them");
                    try {
                        choice = in.nextInt();
                    }
                    catch (InputMismatchException e){
                        System.out.println("that is not a number.");
                        in.next();
                        return this;
                    }
                    app.getAccountService().gift(choiceText,choice);
                case 3:
                    //lookup account information
                    System.out.println("Whose account do you wish to look up?");
                    choiceText = in.nextLine();
                    app.getAccountService().getAccountInfo(choiceText);
                    break;
                case 4:
                    return new MessageScreen();
                case 5:
                    System.out.println("Are you sure you want to close your account? Enter yes" +
                            " if you are certain");
                    choiceText=in.nextLine();
                    in.nextLine();
                    if(choiceText.equals("yes")){
                        app.getAccountService().closeAccount();
                    }
                default:
                    //exit program
                    System.out.println("Signing off");
                    return new EntryScreen();
            }
        }
        in.nextLine();
        System.out.println("Press Enter to continue");
        in.nextLine();

        return this;
    }

    private void menuText(){
        if (isAdmin) {
            System.out.println("Hello Admin");
            System.out.println("1: Delete Account");
            System.out.println("2: Create admin account");
            System.out.println("3: Deposit credit into your account");
            System.out.println("4: Spend credits from your account");
            System.out.println("6: Send credit to another account");
            System.out.println("7: List all accounts info");
            System.out.println("8: Open Messages");
            System.out.println("9: Close account");
            System.out.println("default: Log out");
        }else{
            System.out.println("Hello User");
            System.out.println("1: Spend credits from your account");
            System.out.println("2: Send credit to another account");
            System.out.println("3: Show Account Info");
            System.out.println("4: Open Messages");
            System.out.println("5: Close account");
            System.out.println("default: Log out");
        }
    }

    //created to test screen elements
    public void testInit(Application app){
        this.app= (GameAccountApplication) app;
    }
}
