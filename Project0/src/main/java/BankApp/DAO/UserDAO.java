package BankApp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import BankApp.model.User;

/**********************************************
 *
 * This class will basically retrieve the valid
 * user from the database and it will also update
 * the users balance in his/her account.
 *
 * methods:
 * validUser - method to validate the user
 * getUser - gets the user from the database
 * getUser - to get user by his/her id
 * updateAmount - update user balance
 *********************************************/
public class UserDAO {

    //declare variables
    private final SqlDAO sqlDAO;

    //create instance of SqlDAO for connecting to database
    public UserDAO() {
        sqlDAO = new SqlDAO();
    }

    //validate user
    public boolean validUser(String username, String password) {
        boolean valid = false;
        Connection con = sqlDAO.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (con == null) {
            return valid;
        }
        try {
            ps = con.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                valid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlDAO.close(rs, ps, con);
        }

        return valid;
    }

    //retrieve user username and password from database
    public User getUser(String username, String password) {
        User user = null;
        Connection con = sqlDAO.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (con == null) {
            return user;
        }
        try {
            ps = con.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setId(rs.getInt("id"));
                user.setBalance(rs.getFloat("balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlDAO.close(rs, ps, con);
        }

        return user;
    }

    //retrieve user id from database
    public User getUser(int userId) {
        User user = null;
        Connection con = sqlDAO.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (con == null) {
            return user;
        }
        try {
            ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setId(rs.getInt("id"));
                user.setBalance(rs.getFloat("balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlDAO.close(rs, ps, con);
        }

        return user;
    }

    //update user balance
    public boolean updateAmount(int userId, float amount, boolean deposit) {
        boolean updated = false;
        Connection con = sqlDAO.connect();
        PreparedStatement ps = null;
        if (con == null) {
            return updated;
        }
        try {
            ps = con.prepareStatement("update users set balance = balance " + (deposit ? "+" : "-") + " ? WHERE id = ?");
            ps.setFloat(1, amount);
            ps.setInt(2, userId);
            int updatedCnt = ps.executeUpdate();
            if (updatedCnt > 0) {
                updated = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlDAO.close(ps, con);
        }
        return updated;
    }

}