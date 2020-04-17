package com.company.DAO;

import com.company.banking.UserNameBankAccountIDPair;
import com.company.databaseUtils.PostgresqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserNameBankAccountIDPairDAO implements DAO<UserNameBankAccountIDPair, Integer> {

    private PostgresqlConnection postgresqlConnection = null;

    public UserNameBankAccountIDPairDAO(PostgresqlConnection postgresqlConnection) {
        this.postgresqlConnection = postgresqlConnection;
    }

    @Override
    public Integer save(UserNameBankAccountIDPair obj) {
        return null;
    }

    @Override
    public ArrayList<UserNameBankAccountIDPair> retrieveAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM loginaccountsbankaccounts";
        ArrayList<UserNameBankAccountIDPair> userNameBankAccountIDPairs = new ArrayList<>();

        try {
            connection = postgresqlConnection.getConnection();
            String schema = postgresqlConnection.getDefaultSchema();
            statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userNameBankAccountIDPairs.add(new UserNameBankAccountIDPair(resultSet.getInt("accountID"), resultSet.getString("username")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userNameBankAccountIDPairs;
    }

    @Override
    public UserNameBankAccountIDPair[] retrieveByID(Integer accountID) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM loginaccountsbankaccounts WHERE accountID = ?";
        ArrayList<UserNameBankAccountIDPair> userNameBankAccountIDPairs = new ArrayList<>();

        try {
            connection = postgresqlConnection.getConnection();
            String schema = postgresqlConnection.getDefaultSchema();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userNameBankAccountIDPairs.add(new UserNameBankAccountIDPair(resultSet.getInt("accountID"), resultSet.getString("username")));
            }

            return userNameBankAccountIDPairs.toArray(new UserNameBankAccountIDPair[]{});

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new UserNameBankAccountIDPair[]{};
    }

    @Override
    public void delete(UserNameBankAccountIDPair obj) {

    }

    @Override
    public void update(UserNameBankAccountIDPair newObj) {

    }

    public UserNameBankAccountIDPair[] retrieveByID(String userName) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM loginaccountsbankaccounts WHERE username = ?";
        ArrayList<UserNameBankAccountIDPair> userNameBankAccountIDPairs = new ArrayList<>();

        try {
            connection = postgresqlConnection.getConnection();
            String schema = postgresqlConnection.getDefaultSchema();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userNameBankAccountIDPairs.add(new UserNameBankAccountIDPair(resultSet.getInt("accountID"), resultSet.getString("username")));
            }

            return userNameBankAccountIDPairs.toArray(new UserNameBankAccountIDPair[]{});

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new UserNameBankAccountIDPair[]{};
    }
}
