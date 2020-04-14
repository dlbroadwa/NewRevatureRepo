package com.proj.screens;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdminScreen implements Screen {
    @Override
    public Screen doScreen(Application app) throws FileNotFoundException {
        Scanner scanner = ((SchedulingApplication)app).getScanner();
        File allEvents = new File("C:\\Users\\johnn\\Desktop\\GitJump\\Project_0\\resources\\allEvents");

        Scanner scan = new Scanner(allEvents);

        System.out.println("WELCOME FELLOW ADMIN: Here are your events\n");
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        System.out.println("\nWhat would you like to do? (type number) \n 1) Add new event \n 2) Check event enrollment list");
        int adminNumber = scanner.nextInt();

        while (true) {
            if (adminNumber == 1) {
                return (Screen) new EventCreatorScreen();
            } else if (adminNumber == 2) {
                return new EventEnrollmentScreen();
            } else {
                System.out.println("Not a valid input: try again");
                break;
            }
        }
        return (Screen) new AdminScreen();
    }
}
