package com.Project0.dao;

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
    public void updateUserInfo(Golfer golfer) {

    }

    @Override
    public Golfer viewGolferInfo(Golfer golfer) {
        return null;
    }

    @Override
    public void addScoreToHistory(Golfer golfer, MatchScore score) {

    }

    @Override
    public ArrayList<MatchScore> getGolferScores(Golfer golfer) {
        return null;
    }
}
