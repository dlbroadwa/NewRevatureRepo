package com.ex.dao;

import com.ex.model.Person;
import com.ex.model.Player;
import com.ex.service.ConnectionService;
import com.ex.service.PostgreSQLConnection;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
