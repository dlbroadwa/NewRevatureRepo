package auction.dataaccess;
import auction.models.User;
import auction.services.UserService;
import com.sun.org.apache.bcel.internal.generic.Select;

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

    public UserDAO(){
        this.connectionUtils = new PostGresConnectionUtil();
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
        userService = new UserService();
        String hashedPassword = userService.hashPassword(user.getPassword());
        Boolean exists = ifExists(user.getUserName());
        if (exists){
            System.out.println("User Name taken and cannot be added");
            return true;
        }
        String saveStatement = "INSERT INTO " + connectionUtils.getDefaultSchema() + "." + "users"
                + " (username, password, cardinfo, userrole) VALUES (?,?,?,?)";
        try {
            connection = connectionUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(saveStatement);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, user.getCreditCardNumber());
            preparedStatement.setInt(4, user.getRole());
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
                        resultSet.getInt("userrole")));
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
        if(user.getUserId()==-1){
            user.setUserId(this.findByUserName(user.getUserName()));
        }

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
            preparedStatement.setInt(4, user.getRole());
            preparedStatement.setInt(4, user.getUserId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int findByUserName(String name){
        int result = 0;
        connection = null;
        String findUserId = "SELECT userid FROM " + connectionUtils.getDefaultSchema() + "." + "users"
                + " WHERE username = \'" + name + "\'";
        try
        {
            connection = connectionUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findUserId);
            //ExecuteQuery used for insert,update,delete
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("userid");
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean ifExists(String userName){
                connection = null;
        String checkUsers = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + "users"
                + " WHERE username = \'" + userName + "\'";
        try
        {
            connection = connectionUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(checkUsers);
            //ExecuteQuery used for insert,update,delete
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
