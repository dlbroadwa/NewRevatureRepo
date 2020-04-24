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
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<League> leagues = new ArrayList<>();

//        System.out.printf("DAOIMPL - GolferPassed: %s", golfer.getName());
        try {
            con = connectionUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM league";
                stmt = con.prepareStatement(sql);
                stmt.executeQuery();

                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    ArrayList<Golfer> golfer = new ArrayList<>();
                    String day = rs.getString("playday");
                    String thisDay[] = day.split("-");

                   League temp = new League(
                            rs.getString("name"),
                            LocalDate.of(Integer.parseInt(thisDay[0]), Integer.parseInt(thisDay[1]), Integer.parseInt(thisDay[2])),
                           rs.getInt("weeksduration"),
                           golfer
                    );
                    leagues.add(temp);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return leagues;
        }
    }

    @Override
    public void deleteLeague(League league) throws Exception {

    }

    @Override
    public void addGolferToLeague(Golfer golfer, League league) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean success = false;

//        System.out.printf("DAOIMPL - GolferPassed: %s", golfer.getName());
        try {
            con = connectionUtil.getConnection();
            if (con != null) {
                String sql = "UPDATE golfers SET league = ? WHERE id = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, league.getName());
                stmt.setInt(2, Math.toIntExact(golfer.getUserID()));

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
        }
    }

    @Override
    public void removeGolferFromLeague(Golfer golfer, League league) throws Exception {

    }

    @Override
    public ArrayList<Golfer> getLeagueGolfers(League league) {
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Golfer> golfers = new ArrayList<>();

//        System.out.printf("DAOIMPL - GolferPassed: %s", golfer.getName());
        try {
            con = connectionUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM golfers WHERE league = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, league.getName());
                stmt.executeQuery();

                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    Golfer temp = new Golfer(
                            Long.valueOf(rs.getInt("id")),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("emergencyphone"),
                            rs.getString("carmake"),
                            rs.getString("carmodel"),
                            rs.getString("licenseplate")
                    );
                    golfers.add(temp);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return golfers;
        }
    }

    @Override
    public ArrayList<MatchScore> getLeagueScoresOnDay(League league, LocalDate day) {
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<MatchScore> scores = new ArrayList<>();

//        System.out.printf("DAOIMPL - GolferPassed: %s", golfer.getName());
        try {
            con = connectionUtil.getConnection();
            if (con != null) {
                String sql = "SELECT golfers.name, matchscore.score, matchscore.dayplayed FROM golfers INNER JOIN matchscore ON golfers.name = matchscore.owninggolfer " +
                        "WHERE golfers.league = ? AND matchscore.dayplayed = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, league.getName());
                stmt.setString(2, day.toString());
                stmt.executeQuery();

                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    String date = rs.getString("dayplayed");
                    String dateParsed[] = date.split("-");
                    LocalDate tempDate = LocalDate.of(Integer.parseInt(dateParsed[0]), Integer.parseInt(dateParsed[1]), Integer.parseInt(dateParsed[2]));
                    Golfer tempGolfer = new Golfer();
                    tempGolfer.setName(rs.getString("name"));

                    MatchScore tempScore = new MatchScore();
                    tempScore.setOwningGolfer(tempGolfer);
                    tempScore.setDayPlayed(tempDate);
                    tempScore.setScore(rs.getInt("score"));
                    scores.add(tempScore);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return scores;
        }

    }
}
