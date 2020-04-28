package com.ex.model;

import java.time.LocalDate;

/**
 * The schedule class is a timestamp utility class that displays the time & team to play against
 * @param gameDay - the day/time to play on
 * @param scheduledTeam - the team to go against
 */
public class Schedule {
    private LocalDate gameDay;
    private Team scheduledTeam;

    public Schedule() {
        gameDay = null;
        scheduledTeam = null;
    }

    public Schedule(LocalDate gameDay, Team scheduledTeam) {
        this.gameDay = gameDay;
        this.scheduledTeam = scheduledTeam;
    }

/* =================    GET & SET   ======================= */
    public LocalDate getGameDay() {
        return gameDay;
    }
    public void setGameDay(LocalDate gameDay) {
        this.gameDay = gameDay;
    }
    public Team getScheduledTeam() {
        return scheduledTeam;
    }
    public void setScheduledTeam(Team scheduledTeam) {
        this.scheduledTeam = scheduledTeam;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "gameDay=" + gameDay.toString() +
                ", scheduledTeam=" + scheduledTeam.getName() +
                '}';
    }
}
