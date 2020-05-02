package com.ex.ers.DAO;

import com.ex.ers.models.Person;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
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
            String sql ="Select * from public.persons where username =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                person.setUsername(rs.getString("username"));
                person.setPw(rs.getString("pass"));
                person.setFname(rs.getString("fname"));
                person.setLname(rs.getString("lname"));
                person.setAddress(rs.getString("address"));
                person.setJobTitle(rs.getString("jobtitle"));
                person.setUsername(rs.getString("username"));
                person.setPw(rs.getString("pw"));
                person.setId(rs.getInt("employee_id"));
                person.setManager(rs.getInt("manager"));
            }
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
        Connection conn = null;
        Person person = new Person();
        List<Person> people = new ArrayList();
        try {
            conn = connectionUtils.getConnection();
            String sql ="Select * from public.persons";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                person.setUsername(rs.getString("username"));
                person.setPw(rs.getString("pw"));
                person.setFname(rs.getString("fname"));
                person.setLname(rs.getString("lname"));
                person.setAddress(rs.getString("address"));
                person.setJobTitle(rs.getString("jobtitle"));
                person.setUsername(rs.getString("username"));
                person.setPw(rs.getString("pw"));
                person.setId(rs.getInt("emp_id"));
                person.setManager(rs.getInt("manager"));
                people.add(person);
            }
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

            return people;
        }
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
