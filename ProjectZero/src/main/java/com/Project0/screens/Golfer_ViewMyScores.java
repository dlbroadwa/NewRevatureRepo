package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.GolferDAO;
import com.Project0.dao.GolferDAOImpl_FileIO;
import com.Project0.model.Golfer;
import com.Project0.model.MatchScore;

import java.util.ArrayList;
import java.util.Scanner;

public class Golfer_ViewMyScores implements Screen {
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        GolferDAO dao = new GolferDAOImpl_FileIO();
        Golfer golfer = new Golfer();
        golfer.setName(app.getUser().getUsername());

        ArrayList<Golfer> golfers = new ArrayList<>();
        golfers = dao.viewGolferInfo(golfer);
        //handle array size - SHOULD ONLY BE 1 retrieved from login UserObject
        if(golfers.size() > 1) {
            System.out.println("PROBLEM WITH RETRIEVAL - MORE THAN ONE FOUND");
            return new GolferOptionsMain();
        }
        else if (golfers.size() <= 0) {
            System.out.println("PROBLEM WITH RETRIEVAL - NONE FOUND");
            return new GolferOptionsMain();
        }
        else {
            golfer = golfers.get(0);
            return viewMyScore(golfer);
        }
    }

    public Screen viewMyScore(Golfer golfer) {
        GolferDAO dao = new GolferDAOImpl_FileIO();
        ArrayList<MatchScore> scores = new ArrayList<>();
        scores = dao.getGolferScores(golfer);
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
