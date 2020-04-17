package com.company.DAO;

import com.company.databaseUtils.PostgresqlConnection;
import com.company.loginAccounts.LoginAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginAccountDAO implements DAO<LoginAccount, String> {

    private PostgresqlConnection postgresqlConnection = null;

    public LoginAccountDAO(PostgresqlConnection postgresqlConnection) {
        this.postgresqlConnection = postgresqlConnection;
    }

    @Override
    public String save(LoginAccount obj) {
        return null;
    }

    @Override
    public ArrayList<LoginAccount> retrieveAll() {
        return null;
    }

    @Override
    public LoginAccount[] retrieveByID(String s) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM loginaccounts WHERE username = ?";

        try {
            connection = postgresqlConnection.getConnection();
            String schema = postgresqlConnection.getDefaultSchema();
            statement = connection.prepareStatement(sql);
            statement.setString(1, s);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) return new LoginAccount[]{ new LoginAccount(resultSet.getString("username"), resultSet.getString("pin"), resultSet.getBoolean("admin"))};

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new LoginAccount[]{};
    }

    @Override
    public void delete(LoginAccount obj) {

    }

    @Override
    public void update(LoginAccount newObj) {

    }
}
