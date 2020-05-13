package auction.dataaccess;
import auction.models.User;
import auction.services.UserService;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User, Integer> {
    private ConnectionUtils connectionUtils = null;
    private Connection connection = null;
    private UserService userService = null;
    /**
     *
     * @param connectionUtils
     * @return UserDAO
     * UserDAO constructor
     */
    public UserDAO(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    /**
     *
     * @param user
     * @return boolean
     * attempts to save data into the database and returns a boolean specifying
     * whether or not it was successful
     */
    @Override
    public boolean save(User user) {
        connection = null;
        String hashedPassword =userService.hashPassword(user.getPassword());
        String saveStatement = "INSERT INTO " + connectionUtils.getDefaultSchema() + "." + "users"
                + " (username, password, cardinfo, userrole) VALUES (?,?,?,?)";
        try {
            connection = connectionUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(saveStatement);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, user.getCreditCardNumber());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.executeUpdate();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> retrieveAll() {
        connection = null;
        List<User> userList = new ArrayList<>();
        try {
            connection = connectionUtils.getConnection();
            String selectStatement = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + "users";
            PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt("userid"), resultSet.getString("username"),
                        resultSet.getString("password"), resultSet.getString("cardinfo"),
                        resultSet.getString("userrole")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User retrieveByID(Integer id) {
        connection = null;
        User user = null;
        try{
            connection = connectionUtils.getConnection();
            String selectStatement = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + "users"
                    + " WHERE  userid = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (id == resultSet.getInt(0)) {
                user.setUserId(resultSet.getInt("userid"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setUserName(resultSet.getString("cardinfo"));
                user.setUserName(resultSet.getString("userrole"));
            }
            else
                System.out.println("No user by that id found.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean delete(User user) {
        connection = null;
        String deleteStatement = "DELETE FROM " + connectionUtils.getDefaultSchema() + "." + "users"
                + " WHERE userid = ?";
        try {
            connection = connectionUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.executeUpdate();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        Connection connection = null;
        String updateStatement = "UPDATE " + connectionUtils.getDefaultSchema()
                + " SET username = ? AND password = ? AND cardinfo = ? AND userrole = ? WHERE userid = ?";
        try
        {
            connection = connectionUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getCreditCardNumber());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setInt(4, user.getUserId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
