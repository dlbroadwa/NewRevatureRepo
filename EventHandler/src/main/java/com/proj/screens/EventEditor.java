package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventEditor implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws IOException {
        System.out.println("What would you like to do? \n [1] Add \n [2] Delete \n [3] Log out");
        Scanner scanner = app.getScanner();

        try {
            int input = scanner.nextInt();
            switch(input){
                case 1:
                    return new AddScreen();
                case 2:
                    return new DeleteScreen();
                case 3:
                    return new LoginScreen();
                default:
                    System.out.println("Invalid selection: please try again");
                    return new EventEditor();
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR... CATCHING ON FIRE");
            scanner.nextLine();
            return new AdminScreen();
        }
    }
}
