package com.ex.dao;

import com.ex.model.*;
import com.ex.service.ConnectionService;
import com.ex.service.PostgreSQLConnection;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl_PGR implements PersonDAO{

    private ConnectionService connectionSvc;

    public PersonDAOImpl_PGR() {
        connectionSvc = new PostgreSQLConnection();
    }

    @Override
    public void addPerson(Person person, Boolean isCoach) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        String personType = isCoach ? "coach" : "player";
        String playerSql = "INSERT INTO public.player (name, phone, emergencyphone, phonecarrier, allowsms, parent, age, position, userid) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String coachSql = "INSERT INTO public.coach (name, phone, emergencyphone, phonecarrier, allowsms, userid) values(?, ?, ?, ?, ?, ?)";
        try {
            //initialize connection & prepare statement
            con = connectionSvc.getConnection();
            if (con != null) {
                String sql = isCoach ? coachSql : playerSql;
                stmt = con.prepareStatement(sql);
                createStatement(isCoach, stmt, person);
//                System.out.println(stmt);
                if(stmt.executeUpdate()<=0) {
                    throw new Exception("No user was created");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public PreparedStatement createStatement(Boolean isCoach, PreparedStatement stmt, Person person)throws Exception{
        if(isCoach){

            stmt.setString(1, person.getName());
            stmt.setString(2, person.getPhone());
            stmt.setString(3, person.getEmergencyPhone());
            stmt.setString(4, person.getPhonecarrier().toString());
            stmt.setBoolean(5, person.isAllowTxtMsg());
            stmt.setInt(6, person.getUserId());
        }
        else {
            Player player = (Player)person;
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getPhone());
            stmt.setString(3, player.getEmergencyPhone());
            stmt.setString(4, player.getPhonecarrier().toString());
            stmt.setString(5, Boolean.toString(player.isAllowTxtMsg()));
            stmt.setString(6, player.getParent());
            stmt.setString(7,  Integer.toString(player.getAge()));
            stmt.setString(8, player.getPosition().toString());
            stmt.setInt(6, person.getUserId());
        }
        return stmt;
    }

    /* Gets list of all players */
    public List<Player> getAllPlayers(){
        Connection con = null;
        PreparedStatement stmt = null;
        List<Player> players = new ArrayList<>();

        try {
            //initialize connection & prepare statement
            con = connectionSvc.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM public.players";
                stmt = con.prepareStatement(sql);
                //System.out.println(stmt);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()) {
                    Player tmp = new Player(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("emergencyphone"),
                            PhoneCarrier.valueOf(rs.getString("phonecarrier")),
                            rs.getBoolean("allowsms"),
                            new Team(rs.getString("team")),
                            rs.getInt("userid"),
                            rs.getString("parent"),
                            rs.getInt("age"),
                            Position.valueOf(rs.getString("position"))
                    );
                    players.add(tmp);
                }
            }
            else System.out.println("ERROR CONNECTING TO DATABASE");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return players;
        }
    }

    /* Gets a list of all coaches */
    public List<Person> getAllCoaches(){
        Connection con = null;
        PreparedStatement stmt = null;
        List<Person> coaches = new ArrayList<>();

        try {
            //initialize connection & prepare statement
            con = connectionSvc.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM public.coaches";
                stmt = con.prepareStatement(sql);
                //System.out.println(stmt);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()) {
                    Person tmp = new Person(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("emergencyphone"),
                            PhoneCarrier.valueOf(rs.getString("phonecarrier")),
                            rs.getBoolean("allowsms"),
                            new Team(rs.getString("team")),
                            rs.getInt("userid")
                    );
                    coaches.add(tmp);
                }
            }
            else System.out.println("ERROR CONNECTING TO DATABASE");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return coaches;
        }
    }
}
