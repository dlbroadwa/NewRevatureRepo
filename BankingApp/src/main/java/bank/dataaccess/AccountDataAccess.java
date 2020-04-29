package bank.dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDataAccess {
    private ConnectionUtils connectionUtils;
    private Connection connection = null;
    public AccountDataAccess(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    public void findAllAccounts() {
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select * from " + schemaName + ".users;";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getString("firstname"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

}
