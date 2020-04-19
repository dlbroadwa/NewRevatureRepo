package com.Project0.dao;

import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.model.MatchScore;

import java.time.LocalDate;
import java.util.ArrayList;

public interface LeagueDAO {

    //Create a new league
    public Boolean createLeague(League league) throws Exception;

    //Read all leagues
    public ArrayList<League> getAllLeagues();

    //Delete league
    public void deleteLeague(League league) throws Exception;

    //Add league for golfer
    public void addGolferToLeague(Golfer golfer, League league) throws Exception;

    //remove golfer from league
    public void removeGolferFromLeague(Golfer golfer, League league) throws Exception;

    //get list of golfers for league
    public ArrayList<Golfer> getLeagueGolfers(League league);

    //get playdate for league
    public LocalDate getLeaguePlayDay(League league);

    //get scores for league golfers of certain date
    public ArrayList<MatchScore> getLeagueScoresOnDay(League league, LocalDate day);

}
