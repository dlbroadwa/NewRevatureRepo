package bank.services;

import bank.dataaccess.PostGresConnectionUtil;
import bank.dataaccess.UserDAO;
import bank.model.User;

import java.net.PasswordAuthentication;

/***
 *
 * @author Shawyn Kane
 */
public class UserServices {
    private UserDAO userDAO;

    public UserServices() {
        userDAO = new UserDAO();
    }

    public UserServices(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserServices(PostGresConnectionUtil postGresConnectionUtil) {
        this.userDAO = new UserDAO(postGresConnectionUtil);
    }

    public User retrieveUserByEmail(String email) {
        User[] retrievedUser = userDAO.retrieveByID(email);
        if (retrievedUser.length == 0) return null;
        return retrievedUser[0];
    }

    public boolean areEmailOrPasswordEmptyStrings(String email, String password) {
        return (email.trim().isEmpty() || password.trim().isEmpty());
    }

    public boolean validateUserInformation(User user) {
        if (user.missingInformation()) return false;
        User retrievedUser = retrieveUserByEmail(user.getEmail());
        if (retrievedUser == null) return false;
        return user.equals(retrievedUser);
    }

    public boolean login(String email, String password) {
        if (areEmailOrPasswordEmptyStrings(email, password)) return false;
        User retrievedUser = retrieveUserByEmail(email);
        if (retrievedUser != null && retrievedUser.getEmail().equals(email) && retrievedUser.getPassword().equals(password)) return true;
        return false;
    }
}
