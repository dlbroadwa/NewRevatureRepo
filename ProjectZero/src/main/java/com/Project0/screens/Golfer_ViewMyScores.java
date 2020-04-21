package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.GolferDAO;
import com.Project0.dao.GolferDAOImpl_FileIO;
import com.Project0.model.Golfer;
import com.Project0.model.MatchScore;
import com.Project0.services.GolferService;

import java.util.ArrayList;
import java.util.Scanner;

public class Golfer_ViewMyScores implements Screen {
    @Override
    public Screen doScreen(App app) {
        Golfer golfer = app.getGolferFromLoggedInUser();
        GolferService service = app.getgService();

        /* THIS METHOD TAKES THE CURRENTLY LOGGED IN USER AND LOOKS UP BY NAME
        IN THE DATABASE AND WILL RETURN WHATEVER SCORES THIS USER HAS LOGGED IN DB
         */
        return viewMyScore(golfer, service);
    }

    public Screen viewMyScore(Golfer golfer, GolferService service) {
        ArrayList<MatchScore> scores = new ArrayList<>();
        scores = service.getGolferScores(golfer);
        if(scores.size() > 0) {
            for (MatchScore e : scores) {
                System.out.println(e.toString());
            }
            System.out.println("\n");
            return new GolferOptionsMain();
        }
        else {
            System.out.println("NO SCORES LOGGED FOR YOU");
            return new GolferOptionsMain();
        }
    }
}
