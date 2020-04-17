package com.game.screens;

import com.game.app.Application;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EntryScreen implements Screen {
    public boolean exitCondition = false;
    Application app;

    @Override
    public Screen doScreen(Application app) {
        this.app=app;
        Scanner in = app.getScanner();
        String username;
        String password;
        int choice;
        entryText();
        try {
            choice = in.nextInt();
        }catch (InputMismatchException e){
            e.printStackTrace();
            return this;
        }
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
                System.out.println("Goodbye");
                return null;
        }
        if (exitCondition){
            return new MenuScreen();
        }
        else{
            return this;
        }
    }

    private void entryText() {
        System.out.println("Select your options:");
        System.out.println("1. Log In");
        System.out.println("2. Sign Up");
        System.out.println("default: quit");
    }

    public void logIn(String username, String password){
        if(app.getAccountService().checkCredentials(username, password)){
            exitCondition=true;
        }
    }

    public void signUp(String username, String password){
        if(!app.getAccountService().checkDuplicates(username)){
            exitCondition=true;
            app.getAccountService().signUp(username, password);
        }
    }

    //created to test screen elements
    public void testInit(Application app){
        this.app=app;
    }
}
