package com.game.screens;
import com.game.app.Application;
import com.game.app.GameAccountApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The main screen of the project
 * Originally much larger, so I grouped some of the options
 * making a new screen the user could access.
 */

public class MenuScreen implements Screen {
    GameAccountApplication app;
    boolean isAdmin;

    /**
     * What the application will do when it is on the current screen.
     * @param ap allows getting services from the application layer
     * @return this screen, or another screen based on user input
     */
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

        //different options depending on type of user
        if(isAdmin) {
            switch (choice) {
                case 1:
                    System.out.println("What is the name of the account you wish to delete?");
                    in.nextLine();
                    choiceText = in.nextLine();
                    app.getAccountService().deleteAccount(choiceText);
                    //delete account
                    break;
                case 2:
                    //create admin account
                    System.out.println("Enter the username of the admin account");
                    in.nextLine();
                    choiceText = in.nextLine();
                    System.out.println("Enter Password of the admin account");
                    choiceText2 = in.nextLine();
                    System.out.println("Press enter");
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
                    in.nextLine();
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
                    System.out.println("Whose account do you wish to deposit credits into?");
                    in.nextLine();
                    choiceText = in.nextLine();
                    app.getAccountService().getAccountInfo(choiceText);
                    break;
                case 7:
                    app.getAccountService().list();
                    break;
                case 8:
                    return new MessageScreen();
                case 9:
                    System.out.println("What new password do you want?");
                    in.nextLine();
                    choiceText = in.nextLine();
                    app.getAccountService().changePassword(choiceText);
                    break;
                case 10:
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
                    in.nextLine();
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
                    app.getAccountService().getAccountInfo();
                    break;
                case 4:
                    return new MessageScreen();
                case 5:
                    System.out.println("What new password do you want?");
                    in.nextLine();
                    choiceText = in.nextLine();
                    app.getAccountService().changePassword(choiceText);
                    break;
                case 6:
                    System.out.println("Are you sure you want to close your account? Enter yes" +
                            " if you are certain");
                    in.nextLine();
                    choiceText=in.nextLine();
                    if(choiceText.equals("yes")){
                        app.getAccountService().closeAccount();
                    }
                default:
                    //exit program
                    System.out.println("Signing off");
                    return new EntryScreen();
            }
        }
        /*
         * Allows users to be able to see console messages
         * that resulted from previous option selections
         * When done viewing, the user presses enter
         */
        in.nextLine();
        System.out.println("Press Enter to continue");
        in.nextLine();

        return this;
    }

    /**
     * Simply displays a block of text
     */
    private void menuText(){
        if (isAdmin) {
            System.out.println("Hello Admin");
            System.out.println("1: Delete Account");
            System.out.println("2: Create admin account");
            System.out.println("3: Deposit credit into your account");
            System.out.println("4: Spend credits from your account");
            System.out.println("5: Send credit to another account");
            System.out.println("6: Look up account by name");
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
            System.out.println("5: Change password");
            System.out.println("6: Close account");
            System.out.println("default: Log out");
        }
    }
}
