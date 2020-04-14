package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.GolferDAO;
import com.Project0.dao.GolferDAOImpl_FileIO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GolferOptionsMain implements Screen {
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        GolferDAO dao = new GolferDAOImpl_FileIO();

        System.out.println("GOLFER OPTIONS:");
        if(app.getUserAccessLevel(app.getUser()).equals("admin")) {
            System.out.println("1: Add Golfer");
            System.out.println("2: View Golfer");
            System.out.println("3: Update Golfer");
        }
        else {
            System.out.println("5: Add League Score");
            System.out.println("6: View My Scores");
        }
        System.out.println("7: Change my password");
        if(app.getUserAccessLevel(app.getUser()).equals("user"))
            System.out.println("8: Update my info");
        System.out.println("10: Return to Main Menu");


        try {
            System.out.println("Enter Option number: \n");
            int a = scanner.nextInt();
            if(app.getUserAccessLevel(app.getUser()).equals("admin")) {
                switch (a) {
                    case 1:
                        scanner.nextLine();
                        return new Golfer_AddGolfer();
                    case 2:
                        scanner.nextLine();
                        return new Golfer_ViewGolfer();
                    case 3:
                        scanner.nextLine();
                        return new Golfer_UpdateGolfer();
                }
            }
            if(app.getUserAccessLevel(app.getUser()).equals("user")) {
                switch (a) {
                    case 5:
                        scanner.nextLine();
                        return new Golfer_AddLeagueScore();
                    case 6:
                        scanner.nextLine();
                        return new Golfer_ViewMyScores();
                    case 8:
                        scanner.nextLine();
                        return new Golfer_UpdateMyInfo();
                }
            }
            switch (a) {
                case 7:
                    scanner.nextLine();
                    return new User_ChangeMyPassword();
                case 10:
                    scanner.nextLine();
                    return new MainOptions();
                default:
                    System.out.println("INVALID OPTION - PLEASE USE FROM LIST");
                    scanner.nextLine();
                    return new GolferOptionsMain();
            }
        } catch (InputMismatchException e) {
            System.out.println("INVALID OPTION - PLEASE USE FROM LIST");
            scanner.nextLine();
            return new GolferOptionsMain();
        }
    }
}
