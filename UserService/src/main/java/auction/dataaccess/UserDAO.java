package auction.dataaccess;
import auction.models.User;
import auction.services.AuthenticationService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UserDAO implements DAO<User, Integer> {
    private ConnectionUtils connectionUtils;
    private Connection connection;
    private AuthenticationService authenticationService;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


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
     */
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
        authenticationService = new AuthenticationService();
        preparedStatement = null;
        Boolean exists = ifExists(user.getUserName());

        if (exists){
            System.out.println("User Name taken and cannot be added");
            return true;
        }
        String saveStatement = "INSERT INTO " + connectionUtils.getDefaultSchema() + "." + "users"
                + " (username, password, cardinfo, userrole) VALUES (?,?,?,?)";
        try {
            connection = connectionUtils.getConnection();
            preparedStatement = connection.prepareStatement(saveStatement);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getCreditCardNumber());
            preparedStatement.setInt(4, user.getRole());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
            closeAll(connection, preparedStatement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return
     */
    @Override
    public List<User> retrieveAll() {
        connection = null;
        resultSet = null;
        preparedStatement = null;
        List<User> userList = new ArrayList<>();
        String selectStatement = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + "users";

        try {
            connection = connectionUtils.getConnection();
            preparedStatement = connection.prepareStatement(selectStatement);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)));
            }
            if(resultSet != null) resultSet.close();
            closeAll(connection, preparedStatement);
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User retrieveByID(Integer id) {
        connection = null;
        preparedStatement = null;
        User user = new User();
        resultSet = null;

        if(id==-1){
            user.setUserId(findByUserName(user.getUserName()));
        }

        try{
            connection = connectionUtils.getConnection();
            String selectStatement = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + "users"
                    + " WHERE  userid = " + id;
            preparedStatement = connection.prepareStatement(selectStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUserId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setCreditCardNumber(resultSet.getString(4));
                user.setRole(resultSet.getInt(5));
            }
            else
                System.out.println("No user by that id found.");
            if(resultSet != null) resultSet.close();
            closeAll(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean delete(User user) {
        connection = null;
        preparedStatement = null;
        if(user.getUserId()==-1){
            user.setUserId(findByUserName(user.getUserName()));
        }

        String deleteStatement = "DELETE FROM " + connectionUtils.getDefaultSchema() + "." + "users"
                + " WHERE userid = ?";
        try {
            connection = connectionUtils.getConnection();
            preparedStatement = connection.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.executeUpdate();
            closeAll(connection, preparedStatement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean update(User user) {
        connection = null;
        preparedStatement = null;
        if(user.getUserId()==-1){
            user.setUserId(findByUserName(user.getUserName()));
        }
        else
            System.out.println("Now the userID is: " + user.getUserName());

        String updateStatement = "UPDATE " + connectionUtils.getDefaultSchema() + "." + "users"
                + " SET username = ?, password = ?, cardinfo = ?, userrole = ? WHERE userid = ?";
        try
        {
            connection = connectionUtils.getConnection();
            preparedStatement = connection.prepareStatement(updateStatement);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getCreditCardNumber());
            preparedStatement.setInt(4, user.getRole());
            preparedStatement.setInt(5, user.getUserId());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
            closeAll(connection, preparedStatement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param name
     * @return
     */
    private int findByUserName(String name){
        int result = 0;
        connection = null;
        preparedStatement = null;
        resultSet = null;
        String findUserId = "SELECT userid FROM " + connectionUtils.getDefaultSchema() + "." + "users"
                + " WHERE username = \'" + name + "\'";
        System.out.println(findUserId);
        try
        {
            connection = connectionUtils.getConnection();
            preparedStatement = connection.prepareStatement(findUserId);
            //ExecuteQuery used for select
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
                System.out.println("User found");
            }
            if(resultSet != null) resultSet.close();
            closeAll(connection, preparedStatement);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @param userName
     * @return boolean
     */
    private boolean ifExists(String userName){
        connection = null;
        preparedStatement = null;
        resultSet = null;
        String checkUsers = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + "users"
                + " WHERE username = \'" + userName + "\'";
        try
        {
            connection = connectionUtils.getConnection();
            preparedStatement = connection.prepareStatement(checkUsers);
            //ExecuteQuery used for insert,update,delete
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               return true;
            }
            if(resultSet != null) resultSet.close();
            closeAll(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     *
     * @param connection
     * @param preparedStatement
     */
    private void closeAll(Connection connection, PreparedStatement preparedStatement){
        try {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }//End closeAll
}//End UserDao