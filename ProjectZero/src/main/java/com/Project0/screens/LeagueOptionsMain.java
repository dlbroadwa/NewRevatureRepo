package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.LeagueDAO;
import com.Project0.dao.LeagueDAOImplementation_FileIO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LeagueOptionsMain implements Screen {
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        LeagueDAO dao = new LeagueDAOImplementation_FileIO();

        System.out.println("LEAGUE OPTIONS:");
        if (app.getUserAccessLevel(app.getUser()).equals("admin")) {
            System.out.println("1: Create League");
            System.out.println("2: Delete League");
            System.out.println("3: Add Golfer to League");
            System.out.println("4: Remove Golfer from League");
        }
        System.out.println("5: Get League Golfers");
        System.out.println("6: Get League Play Day");
        System.out.println("7: Get League Scores on Day");
        System.out.println("10: Return to Main Menu");

        try {
            System.out.println("Enter Option number: \n");
            int a = scanner.nextInt();
            if (app.getUserAccessLevel(app.getUser()).equals("admin")) {
                switch (a) {
                    case 1:
                        scanner.nextLine();
                        return new League_CreateLeague();
                    case 2:
                        scanner.nextLine();
                        //return new League_DeleteLeague();
                    case 3:
                        scanner.nextLine();
                        //return new League_AddGolfer();
                    case 4:
                        scanner.nextLine();
//                        return new League_RemoveGolfer();
                }
            }
            switch (a) {
                case 5:
                    scanner.nextLine();
//                    return new League_GetLeagueGolfers();
                case 6:
                    scanner.nextLine();
//                    return new League_GetLeaguePlayDay();
                case 7:
                    scanner.nextLine();
//                    return new League_GetScoresOnDay();
                case 10:
                    scanner.nextLine();
                    return new MainOptions();
                default:
                    System.out.println("INVALID OPTION - PLEASE USE FROM LIST");
                    scanner.nextLine();
                    return new LeagueOptionsMain();
            }
        } catch (InputMismatchException e) {
            System.out.println("INVALID OPTION - PLEASE USE FROM LIST");
            scanner.nextLine();
            return new LeagueOptionsMain();
        }


    }
}
