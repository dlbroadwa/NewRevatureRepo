package BankApp.jdbc.impl;


import BankApp.jdbc.BankAppConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection implements BankAppConnection {


    private static String url =  System.getenv("jdbcUrl") ;;

    private static String user =  System.getenv("dbUser");

    private static String passwd =  System.getenv("dbPassword");

    private static Connection connect;


    public static Connection getInstance(){
        if(connect == null){
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }


    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

}
