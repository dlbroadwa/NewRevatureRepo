package com.Project0.IO;

import org.junit.Before;
import org.junit.Test;
import org.postgresql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCtest {
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Before
    public void startup() {

        }
            String url = "jdbc:postgresql://revdemo.cmyaylobpmky.us-east-2.rds.amazonaws.com:5432/postgres";
            String username = "jean";
            String pword = "okinawa31";
            Connection connection = null;
            {
                try {
                    connection = DriverManager.getConnection(url, username, pword);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (connection != null) {
                    System.out.println("Connection established");
                } else {
                    System.out.println("Connection Failed");
                }
            }


    @Test
    public void isNotNull()
    {
        System.out.println("Dene there");
    }
}

