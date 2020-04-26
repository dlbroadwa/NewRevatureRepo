package com.ex.model;

import java.util.Arrays;

/**
 * This class is for the Team entity
 *
 * @param name - the name of the Team
 * @param coach - the coach of the Team
 * @param players - an array containing the players of the team
 * @param schedule - the team's schedule
 * @param gameScore - the team's scores for the current season
 * @param sponsor - the team's sponsor             *
 */
public class Team {

    private String name;
    private Coach coach;
    private Player[] players;
    private Schedule[] schedule;
    private GameScore[] gameScores;
    private String sponsor;


    /** no arg constructor that sets the Team's String fields to empty strings
     * and the teams object fields to null
     *
     */
    public Team() {
        this.name = "";
        this.Coach = null;
        this.players = null;
        this.schedule = null;
        this.gameScores = null;
        this.sponsor = "";
    }

    /** arged constructor that sets all of the teams feilds according to the data passed
     *
     * @param name
     * @param coach
     * @param players
     * @param schedule
     * @param gameScores
     * @param sponsor
     */
    public Team(String name, Coach coach, Player[] players, Schedule[] schedule, GameScore[] gameScores, String sponsor) {
        this.name = name;
        this.coach = coach;
        this.players = players;
        this.schedule = schedule;
        this.gameScores = gameScores;
        this.sponsor = sponsor;
    }

    /* =================    GET & SET   ======================= */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Coach getCoach() { return coach; }
    public void setCoach(Coach coach) { this.coach = coach; }
    public Player[] getPlayers() { return players; }
    public void setPlayers(Player[] players) { this.players = players; }
    public Schedule[] getSchedule() { return schedule; }
    public void setSchedule(Schedule[] schedule) { this.schedule = schedule; }
    public GameScore[] getGameScores() { return gameScores; }
    public void setGameScores(GameScore[] gameScores) { this.gameScores = gameScores; }
    public String getSponsor() { return sponser; }
    public void setSponsor(String sponser) { this.sponser = sponser; }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", coach=" + coach +
                ", players=" + Arrays.toString(players) +
                ", schedule=" + Arrays.toString(schedule) +
                ", gameScores=" + Arrays.toString(gameScores) +
                ", sponser='" + sponser + '\'' +
                '}';
    }

}
