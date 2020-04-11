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

        System.out.println("GOLFER OPTIONS: \n");
        System.out.println("1: Add Golfer \n");
        System.out.println("2: View Golfer \n");
        System.out.println("3: Update Golfer \n");
        System.out.println("4: Delete Golfer \n");
        System.out.println("5: Return to Main Menu \n");


        try {
            System.out.println("Enter Option number: \n");
            int a = scanner.nextInt();
            switch(a){
            case 1:
                scanner.nextLine();
                return new Golfer_AddGolfer();
            case 2:
//                leagueOptions();
                break;
            case 5:
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

        return null;
    }
}
