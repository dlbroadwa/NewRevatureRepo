package auction.services;

import auction.dataaccess.ConnectionUtils;
import auction.dataaccess.PostGresConnectionUtil;
import auction.dataaccess.UserDAO;
import auction.models.User;
//import auction.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthenticationService {
    private UserDAO userDao;

    public AuthenticationService() {
        ConnectionUtils connectionUtil = new PostGresConnectionUtil();
        this.userDao = new UserDAO(connectionUtil);
    }

    //public AuthenticationService(UserDAO dao) {
       // this.userDao = dao;
    //}

    /**
     *Attempt to login the passed username/password to the database
     * @param username
     * @param password
     * @return - User object of logged in user - null if fails
     */
//    public User loginUser(String username, String password) {
//        User user = null;
//        String passwordHashed = hashPassword(password);
//
//        try {
//            user = (username, passwordHashed);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            return user;
//        }
//    }

//    public boolean addUser(User user){
//        try{
//            userDao.save(user);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}