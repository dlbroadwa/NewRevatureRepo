package com.game.screens;

import com.game.app.Application;
import com.game.app.GameAccountApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MessageScreen implements Screen{

    GameAccountApplication app;
    Scanner in;

    @Override
    public Screen doScreen(Application ap) {

        app = (GameAccountApplication) ap;
        in=app.getScanner();
        int choice;
        String choiceText;
        String choiceText2;

        menuText();
        app.getAccountService().getMessageStatus();

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
                choiceText = in.nextLine();
                in.nextLine();
                System.out.println("What is you're message?");
                choiceText2 = in.nextLine();
                app.getAccountService().send(choiceText, choiceText2);
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
        return this;
    }

    private void menuText(){
        System.out.println("Message Options");
        System.out.println("1: Read all messages");
        System.out.println("2: Send a message");
        System.out.println("3: Delete a message");
        System.out.println("4: Delete all messages");
        System.out.println("Default: Return to main menu");
    }
}
