package com.ex.dao;

import com.ex.model.User;
import com.ex.service.ConnectionService;
import com.ex.service.PostgreSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for all Database CRUD to our postgress database.  This class
 * should only EVER be called/invoked from the UserService class.  It manually invokes a new
 * ConnectionService class of type PostgreSQLConnection - ONLY here.. offering singular invocation
 * and never fear of cross-vendor breakage for switching to a different database vendor
 */
public class UserDAOImpl_PGR implements UserDAO {
    private ConnectionService connectionSvc;

    public UserDAOImpl_PGR( ) {
        connectionSvc = new PostgreSQLConnection();
    }

    /**
     * Attempts to login the passed args to the database
     * @param username
     * @param passwordHashed
     * @return - User object of logged in successfully user... is Null if errors exist
     * @throws Exception - SQL problems OR multiple users retrieved.  There should ONLY ever be one
     *      user that is returned.
     */
    @Override
    public User loginUser(String username, String passwordHashed) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        String schema = connectionSvc.getDefaultSchema();
        User user = null;
        List<User> users = new ArrayList<>();


        try {
            //initialize connection & prepare statement
            con = connectionSvc.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM public.users WHERE username = ? AND password = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, passwordHashed);
                //System.out.println(stmt);

                //send prepared statement and apply resultset
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String uname = rs.getString("username");
                    String upass = rs.getString("password");
                    String uaccess = rs.getString("useraccess");
                    String uemail = rs.getString("email");
                    User tmp = new User(uname, upass, uemail, uaccess);
                    users.add(tmp);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (users.size() > 1 || users.size() <= 0) {
//            for(User e : users) {
//                System.out.printf("UserDAOImpl_PGR(ln55) -- USER RETRIEVED FROM DBASE: %s", e.getUsername());
//            };
            throw new Exception("SQL FETCH ERROR - NUMBER OF USERS RETURNED IS MORE THAN 1");
        } else {
            if (users.get(0).getUsername().equals(username) && users.get(0).getPassword().equals(passwordHashed)) {
                user = users.get(0);
            } else {
                System.out.printf("READ: %s  %s \n", users.get(0).getUsername(), users.get(0).getPassword());
                System.out.printf("PASSED: %s  %s \n", username, passwordHashed);
                throw new Exception("ERROR LOGGING IN");
            }
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return user;
        }
    }
}
