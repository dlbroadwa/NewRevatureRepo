package bank.dataaccess;

import bank.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements DAO<User, String> {
    private PostGresConnectionUtil postGresConnectionUtil = null;
    private String tableName = "users";

    public UserDAO() {
        postGresConnectionUtil = new PostGresConnectionUtil();
    }

    public UserDAO(PostGresConnectionUtil postGresConnectionUtil) {
        this.postGresConnectionUtil = postGresConnectionUtil;
    }

    @Override
    public String save(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "INSERT INTO " + postGresConnectionUtil.getDefaultSchema() + "." + tableName + " (email, firstname, lastname, password, phonenumber, roleid) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = postGresConnectionUtil.getConnection();
            // Setup query
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            // execute query
            statement.executeUpdate();
            // if everything worked without exception or error return the username passed in
            return user.getEmail();

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

    @Override
    public ArrayList<User> retrieveAll() {
        return null;
    }

    @Override
    public User[] retrieveByID(String email) {
        return new User[0];
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }
}
