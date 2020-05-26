package data;

import model.User;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

/**
 * The Data Access Object class for User model, using a String as primary key index.
 */
public class UserRepository implements Repository<User, String> {
    private final ConnectionUtils connectionUtil;

    /**
     * Constructor will be called in the Servlet initiation where a configured ConnectionUtil object will be provided.
     * @param connectionUtils - configured instance of ConnectionUtils
     */
    public UserRepository(ConnectionUtils connectionUtils) {
        this.connectionUtil = connectionUtils;
    }

    /**
     * Queries the database to find one User by column user_id
     * @param user_id - String user_id
     * @return returns a User object
     */
    @Override
    public User findById(String user_id) {
    	Connection connection = null;
    	User user = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.users WHERE user_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                    resultSet.getString("user_id"),
                    resultSet.getString("password"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")
                );
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    	return user;
    }

    /**
     * Queries the database to retrieve all rows in the User table
     * @return returns a List of User objects
     */
    @Override
    public List<User> findAll() {
    	Connection connection = null;
    	List<User> users = new ArrayList<>();
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.users;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                    resultSet.getString("user_id"),
                    resultSet.getString("password"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")
                );
                users.add(user);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    	return users;
    }

    /**
     * Writes to the database a new User object
     * @param obj - a User object
     * @return returns a User object that was written to the database
     */
    @Override
    public User create(User obj) {
    	Connection connection = null;
    	User user = null;
    	try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "INSERT INTO blog.users VALUES (?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, obj.getUsername());
            preparedStatement.setString(2, obj.getPassword());
            preparedStatement.setString(3, obj.getFirstName());
            preparedStatement.setString(4, obj.getLastName());
            if (preparedStatement.executeUpdate() == 1) {
                user = obj;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return user;
    }

    /**
     * Writes to the database to alter an existing row in the User table
     * @param obj - a User object
     * @return returns the User object that was updated in the database
     */
    @Override
    public User update(User obj) {
    	Connection connection = null;
    	User user = null;
    	try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "UPDATE blog.users SET password=?, first_name=?, last_name=? "
            						+ "where user_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, obj.getPassword());
            preparedStatement.setString(2, obj.getFirstName());
            preparedStatement.setString(3, obj.getLastName());
            preparedStatement.setString(4, obj.getUsername());
            if (preparedStatement.executeUpdate() == 1) {
                user = obj;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return user;
    }

    /**
     * Deletes from the database a User object if exists
     * @param obj - User objecet to be deleted
     */
    @Override
    public void delete(User obj) {
    	Connection connection = null;
    	try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "DELETE FROM blog.users WHERE user_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, obj.getUsername());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
