package com.Project0.services;

import com.Project0.dao.LeagueDAO;
import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.model.MatchScore;

import java.time.LocalDate;
import java.util.ArrayList;

public class LeagueService {
    private LeagueDAO dao;

    public LeagueService(LeagueDAO dao) {
        this.dao = dao;
    }

    public Boolean createLeague(League league) {
        try{
            if(!dao.createLeague(league)) {
                return false;
            }
            else return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<League> getAllLeagues() {
        ArrayList<League> leagues = new ArrayList<>();
        try {
            leagues = dao.getAllLeagues();
            if(leagues.size() <= 0) {
                System.out.println("NO LEAGUES FOUND \n");
                return new ArrayList<League>();
            }
            else return leagues;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<League>();
        }
    }

    public Boolean addGolferToLeague(Golfer golfer, League league) {
        try {
            dao.addGolferToLeague(golfer, league);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Golfer> getLeagueGolfers(League league) {
        ArrayList<Golfer> golfers = new ArrayList<>();
        try {
            golfers = dao.getLeagueGolfers(league);
            return golfers;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Golfer>();
        }
    }

    public ArrayList<MatchScore> getScoresOnDay(League league, LocalDate day) {
        ArrayList<MatchScore> scores = new ArrayList<>();
        try{
            scores = dao.getLeagueScoresOnDay(league, day);
            return scores;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<MatchScore>();
        }
    }
}
