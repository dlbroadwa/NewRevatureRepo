package com.Project0.services;

import com.Project0.dao.GolferDAO;
import com.Project0.model.Golfer;
import com.Project0.model.MatchScore;

import java.util.ArrayList;

public class GolferService {
    private GolferDAO gdao = null;

    public GolferService(GolferDAO gdao) {
        this.gdao = gdao;
    }

    //Create new Golfer
    public Boolean createGolfer(Golfer golfer) {
        try{
            return gdao.createGolfer(golfer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //view golfer info by name
    public ArrayList<Golfer> viewGolfer(Golfer golfer) {
        ArrayList<Golfer> golfers = new ArrayList<>();
        try {
//            System.out.printf("PASSED GOLFER INTO SERVICE: %s", golfer.toString());
            golfers = gdao.viewGolferInfo(golfer);
            return golfers;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Golfer>();
        }
    }

    //Update the golfers info
    public Boolean updateGolfer(Golfer oldGolfer, Golfer newGolfer) {
        try {
            gdao.updateGolferInfo(oldGolfer, newGolfer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Add to score history
    public Boolean addGolferScore(Golfer golfer, MatchScore score) {
        try {
            gdao.addScoreToHistory(golfer, score);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //view all scores
    public ArrayList<MatchScore> getGolferScores(Golfer golfer) {
        ArrayList<MatchScore> scores = new ArrayList<>();
        try {
            scores = gdao.getGolferScores(golfer);
            if(scores.size() > 0) {
                return scores;
            }
            else {
                System.out.println("SERVICE::getGolferScores() - SCORELIST IS EMPTY");
                return new ArrayList<MatchScore>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<MatchScore>();
        }
    }


}
