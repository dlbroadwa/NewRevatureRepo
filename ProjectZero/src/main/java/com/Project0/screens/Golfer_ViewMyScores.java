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
        Golfer golfer = app.getGolferFromLoggedInUser();
        return viewMyScore(golfer);
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
