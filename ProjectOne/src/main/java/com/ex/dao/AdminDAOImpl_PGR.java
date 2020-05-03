package com.ex.dao;

import com.ex.model.*;
import com.ex.service.ConnectionService;
import com.ex.service.PostgreSQLConnection;
import com.ex.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl_PGR implements AdminDAO {
    private ConnectionService connectionSvc;

    public AdminDAOImpl_PGR() {
        connectionSvc = new PostgreSQLConnection();
    }

    @Override
    public void changeUserAccessLevel(User user, String accessLevel) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        String thisAccessLevel = "user";

        //regardless of accessLevel - lowercase & return lower... anything that is invalid types put in as 'user'
        switch(accessLevel.toLowerCase()) {
            case "admin":
                thisAccessLevel = "admin";
                break;
            case "coach":
                thisAccessLevel = "coach";
                break;
            case "player":
                thisAccessLevel = "player";
                break;
            default:
                thisAccessLevel = "user";
                break;

        }

        try {
            //initialize connection & prepare statement
            con = connectionSvc.getConnection();
            if (con != null) {
                String sql = "UPDATE public.users SET useraccess=? WHERE id=?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, thisAccessLevel);
                stmt.setInt(2, user.getId());
                //System.out.println(stmt);
                if(stmt.executeUpdate()<=0) {
                    throw new Exception("ERROR - NO ACCESS LEVEL CHANGED");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void resetPasswordToDefault(User user) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;

        //Hash the word 'password' and store this to the database for a password reset
        UserService service = new UserService();
        String hashedPass = service.hashPassword("password");

        try {
            //initialize connection & prepare statement
            con = connectionSvc.getConnection();
            if (con != null) {
                String sql = "UPDATE public.users SET password=? WHERE id=?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, hashedPass);
                stmt.setInt(2, user.getId());
                //System.out.println(stmt);
                if(stmt.executeUpdate()<=0) {
                    throw new Exception("ERROR - NO ACCESS LEVEL CHANGED");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /* Creates a team onto the database - typically called from StartSeason */
    public void createTeam(Team team) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            //initialize connection & prepare statement
            con = connectionSvc.getConnection();
            if (con != null) {
                String sql = "INSERT INTO public.teams (name, coach) VALUES (?, ?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, team.getName());
                stmt.setString(2, team.getCoach().getName());
                //System.out.println(stmt);
                if(stmt.executeUpdate()<=0) {
                    throw new Exception("ERROR - NO ACCESS LEVEL CHANGED");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /* Assigns a player a team - typically called from StartSeason or CoachPortal::RecruitPlayer */
    public void setTeamOnPlayer(Player player, Team team) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            //initialize connection & prepare statement
            con = connectionSvc.getConnection();
            if (con != null) {
                String sql = "UPDATE public.players SET team = ? WHERE id = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, team.getName());
                stmt.setInt(2, player.getId());
                //System.out.println(stmt);
                if(stmt.executeUpdate()<=0) {
                    throw new Exception("ERROR - NO TEAM NAME CHANGED");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /* Assigns a coach to a team - typically called from StartSeason or CoachPortal::RecruitPlayer */
    public void setTeamOnCoach(Person coach, Team team) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            //initialize connection & prepare statement
            con = connectionSvc.getConnection();
            if (con != null) {
                String sql = "UPDATE public.coaches SET team = ? WHERE id = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, team.getName());
                stmt.setInt(2, coach.getId());
                //System.out.println(stmt);
                if(stmt.executeUpdate()<=0) {
                    throw new Exception("ERROR - NO TEAM NAME CHANGED");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
