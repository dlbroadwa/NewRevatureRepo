package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.model.Golfer;
import com.Project0.services.GolferService;

import java.util.ArrayList;
import java.util.Scanner;

public class Golfer_ViewGolfer implements Screen {
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        GolferService service = app.getgService();

        String name;
        Golfer golfer = new Golfer();
        ArrayList<Golfer> golfers = new ArrayList<>();

        System.out.println("VIEW GOLFER INFO");
        System.out.println("Golfer's name: ");
        name = scanner.nextLine();

        golfer.setName(name);
        golfers = service.viewGolfer(golfer);
        for(Golfer e : golfers) {
            System.out.println("FOUND GOLFER: " + e.toString() + "\n");
        }
        if(golfers.size() <= 0)
            System.out.println("NONE FOUND");
        System.out.println("SEARCH AGAIN?  Y or N");
        while(true) {
            String answer = scanner.nextLine().toLowerCase();
            switch (answer) {
                case "y":
                    return new Golfer_ViewGolfer();
                case "n":
                    return new GolferOptionsMain();
                default:
                    System.out.println("INVALID OPTIONS - Y OR N");
                    continue;
            }
        }
    }
}
