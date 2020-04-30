package bank.dataaccess;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDataAccessObject implements AccountDAOI<ID>{
    private ConnectionUtils connectionUtils;
    private Connection connection = null;
    public AccountDataAccessObject(ConnectionUtils connectionUtils) {
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

    @Override
    public boolean create() {
        return false;
    }

    @Override
    public boolean delete(ID id) {
        return false;
    }

    @Override
    public boolean read(ID id) {
        return false;
    }

    @Override
    public boolean update(String username, ID id, double amount) {
        return false;
    }

    @Override
    public boolean transfer(String userName, int userAccountID, double amount, int transferredAccountID) {
        return false;
    }
}
