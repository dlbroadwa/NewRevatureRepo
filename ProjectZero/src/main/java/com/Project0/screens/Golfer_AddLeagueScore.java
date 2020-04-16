package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.GolferDAO;
import com.Project0.dao.GolferDAOImpl_FileIO;
import com.Project0.model.Golfer;
import com.Project0.model.MatchScore;
import com.Project0.services.GolferService;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class Golfer_AddLeagueScore implements Screen {
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        GolferService service = app.getgService();

        LocalDate date = LocalDate.now();
        int score = 0;
        Golfer golfer = new Golfer();
        golfer.setName(app.getUser().getUsername());


        System.out.printf("ADD LEAGUE SCORE WIZARD FOR: %s", app.getUser().getUsername());
        System.out.println("ENTER DATE OF MATCH: YYYY-MM-DD FORMAT");
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

        System.out.println("ENTER YOUR ROUND SCORE TOTAL:");
        while (true) {
            try {
                score = scanner.nextInt();
                if(score <= 26) {
                    System.out.println("WHO DO YOU THINK YOU ARE - COREY PAVIN?");
                    System.out.println("TRY AGAIN.....");
                    return new GolferOptionsMain();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("MUST BE NUMERIC VALUE");
                continue;
            }
        }

        //perform write operations
        MatchScore thisScore = new MatchScore(golfer, score, date);
        try {
            if(service.addGolferScore(golfer, thisScore)) {
                System.out.println("ADDED SCORE SUCCESSFULLY!  GREAT MATCH!");
            }
            return new GolferOptionsMain();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UNABLE TO ADD SCORE");
            return new GolferOptionsMain();
        }
    }
}
