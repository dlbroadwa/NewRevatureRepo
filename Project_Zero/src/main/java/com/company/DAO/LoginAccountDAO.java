package com.company.DAO;

import com.company.databaseUtils.PostgresqlConnection;
import com.company.loginAccounts.LoginAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/***
 * The LoginAccountDAO class implements the DAO interface to provide direct interaction with the database for information relating to the login accounts.
 *
 * @author Shawyn Kane
 */
public class LoginAccountDAO implements DAO<LoginAccount, String> {

    private PostgresqlConnection postgresqlConnection = null;

    /***
     * Sets the postgressqlConnection to the reference passed to it.
     * @param postgresqlConnection
     */
    public LoginAccountDAO(PostgresqlConnection postgresqlConnection) {
        this.postgresqlConnection = postgresqlConnection;
    }

    /***
     * The save(...) method inserts the passed account information into the loginaccounts table in the database to create a new login account.
     * It does not check to see if an account matches the information provided before creating a new account in the database.
     *
     * @author Shawyn Kane
     * @param loginAccount
     * @return
     */
    @Override
    public String save(LoginAccount loginAccount) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "INSERT INTO " + postgresqlConnection.getDefaultSchema() + ".loginaccounts (username, pin, admin) VALUES (?, ?, ?)";

        try {
            connection = postgresqlConnection.getConnection();
            String schema = postgresqlConnection.getDefaultSchema();
            // Setup query
            statement = connection.prepareStatement(sql);
            statement.setString(1, loginAccount.getUserName());
            statement.setString(2, loginAccount.getPin());
            statement.setBoolean(3, loginAccount.isAdmin());
            // execute query
            statement.executeUpdate();
            // if everything worked without exception or error return the username passed in
            return loginAccount.getUserName();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // if something went wrong return null
        return null;
    }

    /***
     * This method is not implemented and is not supported and will throw an UnsupportedOperationException if invoked.
     *
     * @author Shawyn Kane
     * @return
     * @throws UnsupportedOperationException
     */
    @Deprecated
    @Override
    public ArrayList<LoginAccount> retrieveAll() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /***
     *
     * This method retrieves the login credentials (username, pin, and admin).
     * If there is no account with the provided username it will return an LoginAccount array (LoginAccount[]) of length zero.
     * The method will at most return one LoginAccount in an LoginAccount array (Account[]) (or in other words return an LoginAccount array (LoginAccount[]) of length one).
     * This is due to the fact that the accountID is the primary key of the bankaccounts table in the database.
     *
     * @author Shawyn Kane
     * @param username
     * @return
     */
    @Override
    public LoginAccount[] retrieveByID(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM " + postgresqlConnection.getDefaultSchema() + ".loginaccounts WHERE username = ?";

        try {
            connection = postgresqlConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);

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

    /***
     *
     * This delete method deletes the login account that is passed as a parameter, from the loginaccounts table.
     * It does not check to see if the account exists before attempting to delete.
     *
     * @author Shawyn Kane
     * @param loginAccount
     */
    @Override
    public void delete(LoginAccount loginAccount) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM " + postgresqlConnection.getDefaultSchema() + ".loginaccounts WHERE username = ?";

        try {
            connection = postgresqlConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, loginAccount.getUserName());
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
     *
     * This method is not implemented and is not supported and will throw an UnsupportedOperationException if invoked.
     *
     * @author Shawyn Kane
     * @param loginAccount
     * @throws UnsupportedOperationException
     */
    @Deprecated
    @Override
    public void update(LoginAccount loginAccount) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

}
