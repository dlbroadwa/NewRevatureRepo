package bank.dataaccess;


import bank.model.UserNameBankAccountIDPair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/***
 *
 * The UserNameBankAccountIDPairDAO class implements the DAO interface to provide direct interaction with the database for information relating to the user name and accountID pairs.
 *
 * @author Shawyn Kane
 */
public class UserNameBankAccountIDPairDAO implements DAO<UserNameBankAccountIDPair, Integer> {

    private PostGresConnectionUtil postgresqlConnection;

    public UserNameBankAccountIDPairDAO(PostGresConnectionUtil postgresqlConnection) {
        this.postgresqlConnection = postgresqlConnection;
    }

    /***
     *
     * The save(...) method inserts the passed username and bank account id pair information into the usernamebankaccount table in the database to create a new bank account.
     * It does not check to see if an account matches the information provided before creating a new account in the database.
     * @author Shawyn Kane
     * @param obj
     * @return
     */
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

    /***
     * The retrieveAll() method retrieves all the username and bank account id pair information from the database. This is not actually used, even though it has been implemented.
     *
     * @author Shawyn Kane
     * @return
     */
    @Deprecated
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

    /***
     *
     * The retrieveByID(...) method retrieves the username and bank account id pair information from the database for the provided accountID.
     * There could be zero associations, if there is no account with the provided accountID, in which case it will return an UserNameBankAccountIDPair array (UserNameBankAccountIDPair[]) of length zero.
     * If there is an account with the provide accountID then the method will return all username and accountID pairs that have a matching accountID.
     *
     * @author Shawyn Kane
     * @param accountID
     * @return
     */
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

    /***
     * This method deletes the username and accountID pair from the database that associates a particular user login account and a particular bank account.
     * @author Shawyn Kane
     * @param pair
     */
    @Override
    public void delete(UserNameBankAccountIDPair pair) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM " + postgresqlConnection.getDefaultSchema() + ".loginaccountsbankaccounts WHERE accountID = ? AND username = ?";
        ArrayList<UserNameBankAccountIDPair> userNameBankAccountIDPairs = new ArrayList<>();

        try {
            connection = postgresqlConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, pair.getAccountID());
            statement.setString(2, pair.getCustomerID());
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

    /***
     * This version of the overloaded delete method deletes all the username and accountID pair from the database that associates a particular user login account and a particular bank account, that match the username provided as a parameter.
     * @author Shawyn Kane
     * @param username
     */
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

    /***
     * This version of the overloaded delete method deletes all the username and accountID pair from the database that associates a particular user login account and a particular bank account, that match the accountID provided as a parameter.
     * @author Shawyn Kane
     * @param accountID
     */
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

    /***
     * This method is not implemented and is not supported and will throw an UnsupportedOperationException if invoked.
     *
     * @author Shawyn Kane
     * @param newObj
     * @throws UnsupportedOperationException
     */
    @Deprecated
    @Override
    public void update(UserNameBankAccountIDPair newObj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /***
     * This version of the overload method retrieves the username and bank account id pair information from the database for the provided accountID.
     * There could be zero associations, if there is no account with the provided accountID, in which case it will return an UserNameBankAccountIDPair array (UserNameBankAccountIDPair[]) of length zero.
     * If there is an account with the provide accountID then the method will return all username and accountID pairs that have a matching accountID.
     * @param userName
     * @return
     */
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

    /***
     * This method just checks to see if the username and bank acount id pair exists in the database and returns true if the pair exists and false if it does not.
     * @author Shawyn Kane
     * @param pair
     * @return
     */
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
