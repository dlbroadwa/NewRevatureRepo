package com.Project0.dao;

import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.model.MatchScore;
import com.Project0.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LeagueDAO_ImplDB implements LeagueDAO{
    private ConnectionUtil connectionUtil;

    public LeagueDAO_ImplDB(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Override
    public Boolean createLeague(League league) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean success = false;

//        System.out.printf("DAOIMPL - GolferPassed: %s", golfer.getName());
        try {
            con = connectionUtil.getConnection();
            if (con != null) {
                String sql = "INSERT INTO league (name, playday, weeksduration) VALUES (?, ?, ?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, league.getName());
                stmt.setString(2, league.getPlayDay().toString());
                stmt.setInt(3, league.getWeeksDuration());

//                System.out.printf("SQL STATEMENT: %s \n", stmt.toString());
                success = stmt.executeUpdate() > 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return success;
        }
    }

    @Override
    public ArrayList<League> getAllLeagues() {
//                ResultSet rs = stmt.getResultSet();
//                while(rs.next()) {
//                    String day = rs.getString("dayplayed");
//                    String thisDay[] = day.split("-");
//
//                    MatchScore temp = new MatchScore(
//                            golfer,
//                            rs.getInt("score"),
//                            LocalDate.of(Integer.parseInt(thisDay[0]), Integer.parseInt(thisDay[1]), Integer.parseInt(thisDay[2]))
//                    );
//                    scores.add(temp);
//                }

        return null;
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
