package com.Project0.dao;

import com.Project0.util.CustReader;
import com.Project0.util.CustWriter;
import com.Project0.model.Golfer;
import com.Project0.model.MatchScore;

import java.util.ArrayList;
import java.util.Scanner;

public class GolferDAOImpl_FileIO implements GolferDAO{
    @Override
    public void createGolfer(Golfer golfer) throws Exception{
        CustWriter filewriter = new CustWriter();
        try{
            filewriter.newGolfer(golfer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateGolferInfo(Golfer oldGolfer, Golfer newGolfer) throws Exception{
        CustWriter writer = new CustWriter();
        try{
            writer.updateGolfer(oldGolfer, newGolfer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Golfer> viewGolferInfo(Golfer golfer) {
        CustReader reader = new CustReader();
        ArrayList<Golfer> golfers = new ArrayList<>();
        System.out.println("GOLFER NAME PASSED: " + golfer.getName());
        golfers = reader.viewGolferInfo(golfer.getName());
        return golfers;
    }

    @Override
    public void addScoreToHistory(Golfer golfer, MatchScore score) {

    }

    @Override
    public ArrayList<MatchScore> getGolferScores(Golfer golfer) {
        return null;
    }
}
