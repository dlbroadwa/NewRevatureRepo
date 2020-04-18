package com.company.DAO;

import com.company.banking.UserNameBankAccountIDPair;
import com.company.databaseUtils.PostgresqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserNameBankAccountIDPairDAO implements DAO<UserNameBankAccountIDPair, Integer> {

    private PostgresqlConnection postgresqlConnection;

    public UserNameBankAccountIDPairDAO(PostgresqlConnection postgresqlConnection) {
        this.postgresqlConnection = postgresqlConnection;
    }

    @Override
    public Integer save(UserNameBankAccountIDPair obj) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = postgresqlConnection.getConnection();
            statement = connection.prepareStatement("INSERT INTO " + postgresqlConnection.getDefaultSchema() + ".loginaccountsbankaccounts (username, accountid) VALUES (?,?)");
            statement.setString(1, obj.getCustomerID());
            statement.setInt(2, obj.getAccountID());

            statement.executeUpdate();
            return obj.getAccountID();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return OPERATION_FAILED;
    }

    @Override
    public ArrayList<UserNameBankAccountIDPair> retrieveAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM " + postgresqlConnection.getDefaultSchema() + ".loginaccountsbankaccounts";
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
        String sql = "SELECT * FROM " + postgresqlConnection.getDefaultSchema() + ".loginaccountsbankaccounts WHERE accountID = ?";
        ArrayList<UserNameBankAccountIDPair> userNameBankAccountIDPairs = new ArrayList<>();

        try {
            connection = postgresqlConnection.getConnection();
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
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM " + postgresqlConnection.getDefaultSchema() + ".loginaccountsbankaccounts WHERE accountID = ? AND username = ?";
        ArrayList<UserNameBankAccountIDPair> userNameBankAccountIDPairs = new ArrayList<>();

        try {
            connection = postgresqlConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, obj.getAccountID());
            statement.setString(2, obj.getCustomerID());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM " + postgresqlConnection.getDefaultSchema() + ".loginaccountsbankaccounts WHERE username = ?";
        ArrayList<UserNameBankAccountIDPair> userNameBankAccountIDPairs = new ArrayList<>();

        try {
            connection = postgresqlConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int accountID) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM " + postgresqlConnection.getDefaultSchema() + ".loginaccountsbankaccounts WHERE accountID = ?";
        ArrayList<UserNameBankAccountIDPair> userNameBankAccountIDPairs = new ArrayList<>();

        try {
            connection = postgresqlConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountID);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(UserNameBankAccountIDPair newObj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public UserNameBankAccountIDPair[] retrieveByID(String userName) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM " + postgresqlConnection.getDefaultSchema() + ".loginaccountsbankaccounts WHERE username = ?";
        ArrayList<UserNameBankAccountIDPair> userNameBankAccountIDPairs = new ArrayList<>();

        try {
            connection = postgresqlConnection.getConnection();
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

    public boolean relationshipBetweenUserAndAccountExists(UserNameBankAccountIDPair pair) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM " + postgresqlConnection.getDefaultSchema() + ".loginaccountsbankaccounts WHERE username = ? and accountid = ?";
        ArrayList<UserNameBankAccountIDPair> userNameBankAccountIDPairs = new ArrayList<>();

        try {
            connection = postgresqlConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, pair.getCustomerID());
            statement.setInt(2, pair.getAccountID());

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            System.out.println("There was an networking issue. Please try again, later.");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
