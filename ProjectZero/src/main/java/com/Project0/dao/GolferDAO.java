package com.Project0.dao;

import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.model.MatchScore;

import java.util.ArrayList;

public interface GolferDAO {
    //Create new Golfer
    public void createGolfer(Golfer golfer) throws Exception;

    //Update the golfers info
    public void updateUserInfo(Golfer golfer);

    //view golfer info by name
    public Golfer viewGolferInfo(Golfer golfer);

    //Add to score history
    public void addScoreToHistory(Golfer golfer, MatchScore score);

    //view all scores
    public ArrayList<MatchScore> getGolferScores(Golfer golfer);
}
