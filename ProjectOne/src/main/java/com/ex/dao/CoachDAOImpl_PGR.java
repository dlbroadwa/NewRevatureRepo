package com.ex.dao;

import com.ex.model.Player;
import com.ex.model.Position;
import com.ex.model.Sponsor;
import com.ex.model.Team;
import com.ex.service.ConnectionService;
import com.ex.service.PostgreSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * This class is responsible for all Database CRUD to our postgress database.  This class
 * should only EVER be called/invoked from the CoachService class.  It manually invokes a new
 * ConnectionService class of type PostgreSQLConnection - ONLY here.. offering singular invocation
 * and never fear of cross-vendor breakage for switching to a different database vendor
 */
public class CoachDAOImpl_PGR implements CoachDAO {
    private ConnectionService connectionSvc;

    public CoachDAOImpl_PGR() {
        connectionSvc = new PostgreSQLConnection();
    }


    @Override
    public void addSponsor(Sponsor sponsor, Team team) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        String sponsorName = sponsor == null ? "NULL" : sponsor.getName();

        try {
            //initialize connection & prepare statement
            con = connectionSvc.getConnection();
            if (con != null) {
                String sql = "UPDATE public.teams SET sponsor=? WHERE name=?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, sponsorName);
                stmt.setString(2, team.getName());
                //System.out.println(stmt);
                if(stmt.executeUpdate()<=0) {
                    throw new Exception("ERROR - NO SPONSOR WAS ADDED");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setPracticeDay(LocalDate day) throws Exception {

    }

    @Override
    public void addGameScore(int finalScore, Team team) throws Exception {

    }

    @Override
    public void forfeitGame(LocalDate day, Team team) throws Exception {

    }

    @Override
    public void changePlayerPosition(Player player, Position position) throws Exception {

    }

    @Override
    public void addPlayerToTeam(Player player, Team team) throws Exception {

    }

    @Override
    public void removePlayerFromTeam(Player player, Team team) throws Exception {

    }
}
