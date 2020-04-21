package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.model.League;
import com.Project0.model.MatchScore;
import com.Project0.services.LeagueService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class League_GetScoresOnDay implements Screen{
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

        //print list of selectable leagues
        System.out.println("GET LEAGUE SCORES ON DAY");
        System.out.println("PLEASE SELECT LEAGUE FROM LIST BELOW:");
        int leagueSelect = -1;
        for(int itr = 0; itr < leagues.size(); itr++) {
            System.out.printf("%d - %s \n", itr, leagues.get(itr).getName());
        }
        while(true) {
            try {
                leagueSelect = scanner.nextInt();
                if (leagueSelect > leagues.size()) {
                    System.out.println("INVALID OPTION - PLEASE SELECT ONLY FROM LIST ABOVE");
                    continue;
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("INVALID OPTION - PLEASE SELECT ONLY FROM LIST ABOVE");
                continue;
            }
        }

        //print list of selectable dates
        League thisLeague = leagues.get(leagueSelect);
        System.out.println("WHAT DATE TO SEARCH ON? SELECT FROM DAYS BELOW:");
        LocalDate tempArr[] = new LocalDate[thisLeague.getWeeksDuration()];
        Arrays.fill(tempArr,thisLeague.getPlayDay());
        for(int itr = 0; itr < thisLeague.getWeeksDuration(); itr++) {
            tempArr[itr] = tempArr[itr].plusDays(itr * 7);
            System.out.printf("%d - %s \n", itr, tempArr[itr].toString());
        }
        int daySelect = -1;
        while(true) {
            try {
                daySelect = scanner.nextInt();
                if (daySelect > tempArr.length) {
                    System.out.println("INVALID OPTION - SELECT ONLY FROM LIST ABOVE");
                    continue;
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("INVALID OPTION - SELECT ONLY FROM LIST ABOVE");
                continue;
            }
        }

        //display scores + golfers names on this selected day
        LocalDate day = tempArr[daySelect];
        ArrayList<MatchScore> scores = new ArrayList<>();
        scores = service.getScoresOnDay(thisLeague, day);
        System.out.printf("SCORES FOR %s ON DAY %s: \n", leagues.get(leagueSelect).getName(), day.toString());
        if(scores.size() <= 0) {
            System.out.println("NO SCORES LOGGED FOR THIS DAY YET... \n");
            return new LeagueOptionsMain();
        }
        for(MatchScore e : scores) {
            System.out.printf("%s scored a %d \n", e.getOwningGolfer().getName(), e.getScore());
        }
        System.out.println("\n");
        return new LeagueOptionsMain();
    }
}
