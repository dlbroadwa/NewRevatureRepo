package com.ex.service;

import com.ex.dao.CoachDAO;
import com.ex.dao.CoachDAOImpl_PGR;
import com.ex.model.Player;
import com.ex.model.Position;
import com.ex.model.Sponsor;
import com.ex.model.Team;

import java.time.LocalDate;

/**
 * This service class is responsible for all things related to Coach CRUD
 * calls to the database.  Its a generic DAO interface caller - passing specific
 * DAO implementation SINGULARLY in this class - - allowing for interchangeability
 *  of database types in future by simply adding a new class type here for the following:
 * @param coachDao - this class archetype is for vendor-specific CoachDAO implementations
 */
public class CoachService {
    private CoachDAO coachDao;

    public CoachService() {
        this.coachDao = new CoachDAOImpl_PGR();  //change this impl for different vendor types
    }

    public CoachService(CoachDAO coachDao) {
        this.coachDao = coachDao;
    }

    /**
     * Add a sponsor to the appropriate team.  Leave sponsor null if you want to erase the sponsor
     * @param sponsor - sponsor to add - leave null if you want to erase
     * @param team - the team to add sponsor too
     * @return - success of DAO call
     */
    public boolean addSponsor(Sponsor sponsor, Team team) {
        try{
            coachDao.addSponsor(sponsor, team);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setPracticeDay(LocalDate day) {
        try{
            coachDao.setPracticeDay(day);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }    }

    public boolean addGameScore(int finalScore, Team team) {
        try{
            coachDao.addGameScore(finalScore, team);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean forfeitGame(LocalDate day, Team team) {
        try{
            coachDao.forfeitGame(day, team);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changePlayerPosition(Player player, Position position) {
        try{
            coachDao.changePlayerPosition(player, position);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addPlayerToTeam(Player player, Team team) {
        try{
            coachDao.addPlayerToTeam(player, team);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removePlayerFromTeam(Player player, Team team) {
        try{
            coachDao.removePlayerFromTeam(player, team);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
