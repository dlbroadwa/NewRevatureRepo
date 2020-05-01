package com.ex.dao;

import com.ex.model.Player;
import com.ex.model.Position;
import com.ex.model.Sponsor;
import com.ex.model.Team;

import java.time.LocalDate;

public interface CoachDAO {
    /**
     * Add a sponsor to the appropriate team.  Leave sponsor null if you want to erase the sponsor
     * @param sponsor - sponsor to add - leave null if you want to erase
     * @param team - the team to add sponsor too
     * @return - success of DAO call
     */
    public void addSponsor(Sponsor sponsor, Team team) throws Exception;

    /* Coach establishes a practice day for the team */
    public void setPracticeDay(LocalDate day) throws Exception;

    /* Coach from one team needs to input scores into portal */
    public void addGameScore(int finalScore, Team team) throws Exception;

    /* Coach needs to flag as forfeit for game */
    public void forfeitGame(LocalDate day, Team team) throws Exception;

    /* Coach needs to modify positions on the passed player */
    public void changePlayerPosition(Player player, Position position) throws Exception;

    /* Coach needs to add a player to the roster */
    public void addPlayerToTeam(Player player, Team team) throws Exception;

    /* Coach needs to remove a player from the team */
    public void removePlayerFromTeam(Player player, Team team) throws Exception;
}
