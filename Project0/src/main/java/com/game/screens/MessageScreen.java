package com.game.screens;

import com.game.app.Application;
import com.game.app.GameAccountApplication;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Message screen created so that the user could access functions
 * that are dedicated towards managing message objects
 */
public class MessageScreen implements Screen{

    GameAccountApplication app;

    /**
     *Main function of the MessageScreen
     * is called in the application layer
     */
    @Override
    public Screen doScreen(Application ap) {

        app = (GameAccountApplication) ap;
        int choice;
        String choiceText;
        String choiceText2;

        menuText();
        System.out.println("You have "+app.getAccountService().getMessageNumber()+" messages");

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

        switch (choice) {
            case 1:
                app.getAccountService().readMessages();
                break;
            case 2:
                System.out.println("Who do you want to send a message to?");
                in.nextLine();
                choiceText = in.nextLine();
                System.out.println("What is you're message?");
                choiceText2 = in.nextLine();
                app.getAccountService().send(choiceText, choiceText2);
                System.out.println("Press Enter to continue");
                break;
            case 3:
                System.out.println("What is the index of the message you wish to delete?");
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
                app.getAccountService().delete(choice);
                break;
            case 4:
                app.getAccountService().deleteAll();
                break;
            default:
                return new MenuScreen();
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
     * simply prints out the options
     */
    private void menuText(){
        System.out.println("Message Options");
        System.out.println("1: Read all messages");
        System.out.println("2: Send a message");
        System.out.println("3: Delete a message");
        System.out.println("4: Delete all messages");
        System.out.println("Default: Return to main menu");
    }
}
