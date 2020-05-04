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
        Person person = null;
        try {
            conn = connectionUtils.getConnection();
            String sql = "Select * from public.persons where username =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
    public List<Person> findAll() {
        Connection conn = null;
        Person person = new Person();
        List<Person> people = new ArrayList();
        try {
            conn = connectionUtils.getConnection();
            String sql = "Select * from public.persons";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
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
    public int save(Person obj) {
        Connection conn = null;
        int status = 0;
        PreparedStatement ps = null;
        String sql = "insert into public.persons (fname, lname, address, jobtitle, username, pw) values (?,?,?,?,?,?)";
        try {
            conn = connectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getFname());
            ps.setString(2, obj.getLname());
            ps.setString(3, obj.getAddress());
            ps.setString(4, obj.getJobTitle());
            ps.setString(5, obj.getUsername());
            ps.setString(6, obj.getPw());
            ps.executeUpdate();
            status = 1;
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
            return status;
        }
    }

    @Override
    public int update(Person obj) { //searching by id number
        Connection conn = null;
        int status = 0;
        PreparedStatement ps = null;
        String sql = "UPDATE public.persons SET fname=?, lname=?, address=?, jobtitle=?, username=?, pw=? WHERE emp_id=? ";
        try {
            conn = connectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getFname());
            ps.setString(2, obj.getLname());
            ps.setString(3, obj.getAddress());
            ps.setString(4, obj.getJobTitle());
            ps.setString(5, obj.getUsername());
            ps.setString(6, obj.getPw());
            ps.setInt(7,obj.getId());
            ps.executeUpdate();
            status = 1;
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


            return status;
        }
    }

    @Override
    public List<Person> findAllForName(String s) {
        return null;
    } //don't use

    @Override
    public List<Person> findAllByID(int id) { //don't use this
        return null;
    }


}
