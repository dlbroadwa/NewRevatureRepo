package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  Project 2:<br>
 * <br>
 *  PostgresConnectionUtil
 *
 *  <br> <br>
 *  Created: <br>
 *     May 11, 2020 Ray Vakil<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *
 * <br>
 *  @author
 *  @version 11 May 2020
 */
public class PostgresConnectionUtil extends ConnectionUtil {
//Inject DriverManager from postgresql
    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

//Constructors
    public PostgresConnectionUtil() {
        defaultSchema = "project2";
    }
    public PostgresConnectionUtil(String url, String username, String password, String schema) {
        ConnectionUtil.url = url;
        ConnectionUtil.username = username;
        ConnectionUtil.password = password;
        defaultSchema = schema;
    }

//Override interface method
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}