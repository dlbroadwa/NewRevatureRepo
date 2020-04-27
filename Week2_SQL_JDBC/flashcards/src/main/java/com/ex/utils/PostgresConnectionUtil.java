package com.ex.utils;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
=======
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
>>>>>>> 192af901a827dbec703b30a72dbe36fb2a2de26a

public class PostgresConnectionUtil extends ConnectionUtils {

  static {
    try {
      DriverManager.registerDriver(new org.postgresql.Driver());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public PostgresConnectionUtil() {
    this.defautlSchema = "public";
  }

  public PostgresConnectionUtil(String url, String username, String password, String schema) {
    this.url = url;
    this.username = username;
    this.password = password;
    this.defautlSchema = schema;
  }

<<<<<<< HEAD
=======
  public PostgresConnectionUtil(Properties properties) {
  }

  public PostgresConnectionUtil(FileReader propsInput) {

  }

>>>>>>> 192af901a827dbec703b30a72dbe36fb2a2de26a

  @Override
  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, username, password);
  }
}
