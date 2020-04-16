package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserScreen implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws FileNotFoundException {
        Scanner scanner = app.getScanner();
        File events = new File("C:\\Users\\johnn\\Desktop\\GitJump\\EventHandler\\resources\\events");
        Scanner scan = new Scanner(events);


        System.out.println("WELCOME TO YOUR EVENT HANDLER!");
            System.out.println(scan.nextLine());

        System.out.println("\nWhat would you like to do? (type number) \n 1) Sign up for an event \n 2) Check on your events");


        try {
            int adminNumber = scanner.nextInt();
            switch(adminNumber){
                case 1:
                    return new EventEditor();
                case 2:
                    return new EventEnrollment();
                default:
                    System.out.println("Invalid selection: please try again");
                    return new AdminScreen();
            }
        } catch (InputMismatchException e) {
            System.out.println("INVALID OPTION - PLEASE CHOOSE FROM LIST");
            scanner.nextLine();
            return new AdminScreen();
        }
    }
}