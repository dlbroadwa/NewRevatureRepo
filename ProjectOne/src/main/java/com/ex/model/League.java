package com.ex.model;

import java.util.Arrays;

/**
 * This class models a league object
 *
 *
 *
 * @param city - the city the league is located
 * @param teams - the teams in the league
 */
public class League {

    private String city;
    private String[] teams;


    /** no arg constructor that sets the League's fields to empty strings
     *
     */
    public League() {
        this.city = city;
        this.teams = teams;
    }

    /** arged constructor that sets all fields according to the data passed
     *
     * @param city
     * @param teams
     */
    public League(String city, String[] teams) {
        this.city = city;
        this.teams = teams;
    }

    /* =================    GET & SET   ======================= */

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String[] getTeams() { return teams; }
    public void setTeams(String[] teams) { this.teams = teams; }

    @Override
    public String toString() {
        return "League{" +
                "city='" + city + '\'' +
                ", teams=" + Arrays.toString(teams) +
                '}';
    }
}
