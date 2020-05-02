package bank.services;

import bank.dataaccess.PostGresConnectionUtil;
import bank.dataaccess.UserDAO;
import bank.model.User;

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

    public boolean areEmailOrPasswordEmptyStrings(User user) {
        return (user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty());
    }

    public boolean validateLogin(User user) {
        if (user.missingInformation()) return false;
        User[] retrievedUser = userDAO.retrieveByID(user.getEmail());
        if (retrievedUser.length == 0) return false;
        return user.equals(retrievedUser[0]);
    }
}
