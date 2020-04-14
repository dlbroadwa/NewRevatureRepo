package com.Project0.dao;

import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.model.MatchScore;
import com.Project0.util.CustWriter;

import java.time.LocalDate;
import java.util.ArrayList;

public class LeagueDAOImplementation_FileIO implements LeagueDAO{
    @Override
    public void createLeague(League league) throws Exception {
        CustWriter writer = new CustWriter();
        try {
            writer.createLeague(league);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLeague(League league) throws Exception {

    }

    @Override
    public void addGolferToLeague(Golfer golfer, League league) throws Exception {

    }

    @Override
    public void removeGolferFromLeague(Golfer golfer, League league) throws Exception {

    }

    @Override
    public ArrayList<Golfer> getLeagueGolfers(League league) {
        return null;
    }

    @Override
    public LocalDate getLeaguePlayDay(League league) {
        return null;
    }

    @Override
    public ArrayList<MatchScore> getLeagueScoresOnDay(LocalDate day) {
        return null;
    }
}
