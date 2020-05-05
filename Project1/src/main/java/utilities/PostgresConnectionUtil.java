package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionUtil extends utilities.ConnectionUtils {

  static {
    try {
      DriverManager.registerDriver(new org.postgresql.Driver());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public PostgresConnectionUtil() {
    this.defautlSchema = "myschema";
  }

  public PostgresConnectionUtil(String url, String username, String password, String schema) {
    this.url = url;
    this.username = username;
    this.password = password;
    this.defautlSchema = schema;
  }


  @Override
  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, username, password);
  }
}
