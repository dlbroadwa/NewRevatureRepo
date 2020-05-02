package com.ex.ers.DAO;

import com.ex.ers.models.Person;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonDAO implements DAOs<Person> {
    private ConnectionUtils connectionUtils;

    public PersonDAO() {
        connectionUtils = new PostgresqlConnectionUtil();
    }



    @Override
    public Person findByName(String s) { //s is username
        Connection conn = null;
        Person person = new Person();
        try {
            conn = connectionUtils.getConnection();
            String sql ="Select * from public.person where username =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s);
            ResultSet rs = ps.executeQuery();
            rs.next();
            person.setFname(rs.getString("fname"));
            person.setLname(rs.getString("lname"));
            person.setAddress(rs.getString("address"));
            person.setJobTitle(rs.getString("jobtitle"));
            person.setUsername(rs.getString("username"));
            person.setPw(rs.getString("pw"));
            person.setId(rs.getInt("employee_id"));
//            person.setManager(rs.getBoolean("manager")); //need to add this column to db

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return person;
        }
    }

    @Override
    public Person findByID(int id) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public List<Person> findAllForName(String s) {
        return null;
    }

    @Override
    public int save(Person obj) {
        return 0;
    }

    @Override
    public int update(int id) {
        return 0;
    }
}
