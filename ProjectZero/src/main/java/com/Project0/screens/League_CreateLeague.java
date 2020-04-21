package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.LeagueDAO;
import com.Project0.dao.LeagueDAOImplementation_FileIO;
import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.services.LeagueService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class League_CreateLeague implements Screen{
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        LeagueService service = app.getlService();

        String name;
        LocalDate date = LocalDate.now();
        int duration = 0;
        ArrayList<Golfer> golfers = new ArrayList<>();

        //ENTER LEAGUE NAME TO CREATE NEW LEAGUE
        System.out.println("CREATE LEAGUE WIZARD");
        System.out.println("ENTER LEAGUE NAME:");
        while(true){
            try{
                name = scanner.nextLine();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR WITH NAME - TRY AGAIN");
                continue;
            }
        }

        //ENTER THE DATE - MUST BE IN FORMAT YYYY-MM-DD - REGEX WILL CATCH THIS
        System.out.println("ENTER PLAY ON DATE - YYYY-MM-DD FORMAT");
        while(true) {
            String dateString = scanner.nextLine();
            String dateParsed[] = dateString.split("-");
            if (dateParsed.length == 3 && dateParsed[0].matches("\\d{4}") && dateParsed[1].matches("\\d{2}") && dateParsed[2].matches("\\d{2}")) {
                date = LocalDate.of(Integer.parseInt(dateParsed[0]), Integer.parseInt(dateParsed[1]), Integer.parseInt(dateParsed[2]));
                break;
            } else {
                System.out.println("INCORRECT FORMAT - TRY AGAIN? Y or N");
                continue;
            }
        }

        //NUMBER OF WEEKS TO GO FOR - HAS TO BE BETWEEN 0 & 52 - CANT GO FOR LONGER THAN 1 YEAR... THATS JUST TOO MUCH GOLF!
        System.out.println("ENTER HOW MANY WEEKS THIS LEAGUE SHOULD GO FOR?");
        while(true) {
            try {
                duration = scanner.nextInt();
                if(duration <= 0 || duration > 52) {
                    System.out.println("NOT VALID WEEKS - PLEASE ENTER FROM 0~52");
                    continue;
                }
                break;
            } catch (InputMismatchException e){
                e.printStackTrace();
                System.out.println("NOT A VALID NUMBER FORMAT - PLEASE TRY AGAIN");
                continue;
            }
        }

        //create league and write to file/db
        League league = new League(name, date, duration, golfers);
        try{
            if(service.createLeague(league))
                System.out.println("LEAGUE CREATED SUCCESSFULLY!!");
            else
                System.out.println("ERROR CREATING LEAGUE");
            return new LeagueOptionsMain();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR CREATING LEAGUE");
            return new LeagueOptionsMain();
        }
    }
}
