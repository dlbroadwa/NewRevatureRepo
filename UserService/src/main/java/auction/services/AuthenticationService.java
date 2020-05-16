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
//            user = userDao.loginUser(username, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            return user;
//        }
//    }

    /**
     * @param password
     * @return - Hash password to be used for login or changing password
     */
    public String hashPassword(String password) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(password.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for(int itr = 0; itr < hashedBytes.length; itr++) {
                byte b = hashedBytes[itr];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }

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