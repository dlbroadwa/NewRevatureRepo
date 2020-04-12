package com.Project0.screens;

import com.Project0.application.App;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainOptions implements Screen{
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();

        System.out.println("MAIN OPTIONS");
        System.out.println("1: Golfer Options \n");
        System.out.println("2: League Options \n");
        System.out.println("3: Exit Program");
        System.out.println("Enter Option number: \n");
        try {
            int a = scanner.nextInt();
            switch(a){
                case 1:
                    return new GolferOptionsMain();
                case 2:
                    return new LeagueOptionsMain();
                case 3:
                    return null;
                default:
                    System.out.println("INVALID OPTION - PLEASE CHOOSE FROM LIST");
                    scanner.nextLine();
                    return new MainOptions();
            }
        } catch (InputMismatchException e) {
            System.out.println("INVALID OPTION - PLEASE CHOOSE FROM LIST");
            scanner.nextLine();
            return new MainOptions();
        }
    }
}
