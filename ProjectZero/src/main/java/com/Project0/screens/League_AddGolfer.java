package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.LeagueDAO;
import com.Project0.dao.LeagueDAOImplementation_FileIO;
import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.services.GolferService;
import com.Project0.services.LeagueService;

import java.util.ArrayList;
import java.util.Scanner;

public class League_AddGolfer implements Screen {
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        LeagueService service = app.getlService();
        GolferService gService = app.getgService();

        League thisLeague = null;


        ArrayList<League> leagues = new ArrayList<>();
        int leagueSelect = -1;
        //failout if there are no leagues yet in dbase
        leagues = service.getAllLeagues();
        if(leagues.size() <= 0){
            System.out.println("THERE ARE NO LEAGUES TO ADD PLAYERS TO ATM - PLEASE CREATE A NEW LEAGUE");
            return new LeagueOptionsMain();
        }

        //entrance output
        System.out.println("ADD GOLFER TO A LEAGUE WIZARD");
        System.out.println("SELECT WHICH LEAGUE YOU WANT TO ADD TO:");
        //iterate leagues from DB return & print out in selectable list fashion
        for(int itr = 0; itr < leagues.size(); itr++) {
            System.out.printf("%d - %s \n", itr, leagues.get(itr).getName());
        }
        while(true) {
            try {
                leagueSelect = scanner.nextInt();
                if(leagueSelect > leagues.size()) {
                    System.out.println("ONLY CHOOSE OPTIONS FROM THIS LIST");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("INVALID OPTION TYPE - PLEASE SELECT FROM NUMBERED LIST ABOVE");
                continue;
            }
        }

        //move to add a golfer by name - searching database and returning list of golfers matching name
        thisLeague = leagues.get(leagueSelect);
        ArrayList<Golfer> golfers = new ArrayList<>();
        Golfer temp = new Golfer();
        System.out.printf("ADDING TO %s \n", leagues.get(leagueSelect).getName());
        System.out.println("ENTER GOLFERS NAME TO ADD TO LEAGUE:");
        String golferName;
        scanner.nextLine();
        while(true) {
            try {
                golferName = scanner.nextLine();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        temp.setName(golferName);
        golfers = gService.viewGolfer(temp);
        if(golfers.size() == 0){
            System.out.println("ERROR FINDING GOLFER IN SYSTEM");
            return new LeagueOptionsMain();
        }

        //INPUT - ask user to select from golfers matching
        System.out.println("PLEASE SELECT GOLFER FROM MATCHING GOLFERS:");
        //create list of golers to choose from
        for(int itr = 0; itr < golfers.size(); itr++) {
            System.out.printf("%d - %s \n", itr, golfers.get(itr).getName());
        }
        int golferSelect = -1;
        while(true) {
            try {
                golferSelect = scanner.nextInt();
                break;
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT - SELECT NUMBER FROM LIST");
                continue;
            }
        }

        //Build golfer from DB read &
        Golfer thisGolfer = golfers.get(golferSelect);
        if(service.addGolferToLeague(thisGolfer, thisLeague)){
            System.out.println("ADDED GOLFER SUCCESSFULLY! \n");
            return new LeagueOptionsMain();
        }
        else {
            System.out.println("PROBLEM ADDING GOLFER \n");
            return new LeagueOptionsMain();
        }
    }
}
