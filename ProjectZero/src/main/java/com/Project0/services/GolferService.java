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

    public Boolean createGolfer(Golfer golfer) {
        try{
            return gdao.createGolfer(golfer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Golfer> viewGolfer(Golfer golfer) {
        try {
//            System.out.printf("PASSED GOLFER INTO SERVICE: %s", golfer.toString());
            ArrayList<Golfer> golfers = new ArrayList<>();
            golfers = gdao.viewGolferInfo(golfer);
            return golfers;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FUCKING UP");
            return new ArrayList<Golfer>();
        }
    }

    public Boolean updateGolfer(Golfer oldGolfer, Golfer newGolfer) {
        try {
            gdao.updateGolferInfo(oldGolfer, newGolfer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean addGolferScore(Golfer golfer, MatchScore score) {
        try {
            gdao.addScoreToHistory(golfer, score);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
