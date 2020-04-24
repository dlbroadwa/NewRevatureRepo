package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.model.League;
import com.Project0.services.LeagueService;

import java.util.ArrayList;
import java.util.Scanner;

public class League_GetLeaguePlayDay implements Screen{
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        LeagueService service = app.getlService();

        //FAILOUT - EXIT THIS SCREEN IF THERE ARE NO LEAGUES IN THE SYSTEM YET
        ArrayList<League> leagues = new ArrayList<>();
        leagues = service.getAllLeagues();
        if(leagues.size() <= 0) {
            System.out.println("THERE ARE NO LEAGUES IN SYSTEM YET...");
            return new LeagueOptionsMain();
        }

        //SELECT FROM PRODUCED LIST OF LEAGUES IN DBASE
        System.out.println("GET LEAGUE PLAY ON DAY");
        System.out.println("PLEASE SELECT LEAGUE FROM LIST BELOW:");
        int leagueSelect = -1;
        for(int itr = 0; itr < leagues.size(); itr++) {
            System.out.printf("%d - %s \n", itr, leagues.get(itr).getName());
        }
        while(true) {
            try {
                leagueSelect = scanner.nextInt();
                if (leagueSelect > leagues.size() - 1) {
                    System.out.println("INVALID OPTION - PLEASE SELECT ONLY FROM LIST ABOVE");
                    scanner.nextLine();
                    continue;
                }
                else break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("INVALID OPTION - PLEASE SELECT ONLY FROM LIST ABOVE");
                scanner.nextLine();
                continue;
            }
        }

        //RETURN WHAT DAY THIS LEAGUE PLAYS ON - RETURN TO LEAGUE OPTIONS MENU
        League thisLeague = leagues.get(leagueSelect);
        System.out.printf("%s LEAGUE PLAYS ON DAY: %s \n", thisLeague.getName(), thisLeague.getPlayDay().toString());
        return new LeagueOptionsMain();
    }
}
