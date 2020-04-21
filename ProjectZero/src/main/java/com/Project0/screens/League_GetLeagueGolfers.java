package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.services.LeagueService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class League_GetLeagueGolfers implements Screen{
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        LeagueService service = app.getlService();

        //exit out of window if there are no leagues in dbase yet
        ArrayList<League> leagues = new ArrayList<>();
        leagues = service.getAllLeagues();
        if(leagues.size() <= 0) {
            System.out.println("");
        }

        //PRINT A LIST OF LEAGUES TO CHOOSE FROM
        System.out.println("VIEW GOLFERS OF SPECIFIC LEAGUE");
        System.out.println("ENTER LEAGUE NAME FROM CHOICE BELOW:");
        for(int itr = 0; itr < leagues.size(); itr++) {
            System.out.printf("%d - %s \n", itr, leagues.get(itr).getName());
        }
        int leagueSelect = -1;
        while(true) {
            try{
                leagueSelect = scanner.nextInt();
                if(leagueSelect > leagues.size() -1) {
                    System.out.println("INVALID OPTION - PLEASE SELECT FROM LIST ABOVE");
                    continue;
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("INVALID OPTION - PLEASE ONLY USE NUMBERS");
                scanner.nextLine();
                continue;
            }
        }

        //print off all the golfers belonging to selected league
        League selectedLeague = leagues.get(leagueSelect);
        ArrayList<Golfer> golfers = new ArrayList<>();
        golfers = service.getLeagueGolfers(selectedLeague);
        scanner.nextLine();
        System.out.printf("GOLFERS IN LEAGUE - %s - QTY: %d \n", selectedLeague.getName(), golfers.size());
        for(int itr = 0; itr < golfers.size(); itr++) {
            System.out.printf("%s \n", golfers.get(itr).getName());
        }

        //prompt to search again or return?
        System.out.println("SEARCH AGAIN? Y or N");
        while(true) {
            String input = scanner.nextLine();
            input.toLowerCase();
            switch (input) {
                case "y":
                    return new League_GetLeagueGolfers();
                case "n":
                    return new LeagueOptionsMain();
                default:
                    System.out.println("INVALID INPUT - PLEASE Y or N ONLY");
                    continue;
            }
        }
    }
}
