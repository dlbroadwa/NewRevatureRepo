package com.Project0.services;

import com.Project0.dao.LeagueDAO;
import com.Project0.model.League;

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
}
