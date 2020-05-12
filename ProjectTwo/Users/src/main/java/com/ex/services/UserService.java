package com.ex.services;


import com.ex.dao.UserDAO;
import com.ex.dao.UserDAOImpl_PGR;
import com.ex.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * The UserServices class is a form of data persistence.
 * methods (CRUD methods) are to be called to change the data tables inside our AWS RDB.
 * It will create, read, update or delete a user to the users table in our RDB.
 * Create user, update user, display user information, delete user, as well as login user.
 * Storing the changes for later use inside the AWS RDB.
 *
 */

public class UserService {
    private UserDAO userDao;

    public UserService() {
        this.userDao = new UserDAOImpl_PGR();
    }

    public UserService(UserDAO dao) {
        this.userDao = dao;
    }

    /**
     * This function allows for immediate hashing of a password input by the user - should
     * be used IMMEDIATELY with no storage of the raw password input from the user.
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

    public User loginUser(String username, String hashedPassword) {
        User user = null;
        try {
            user = userDao.loginUser(username, hashedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return user;
        }
    }
//********************************************
//    ADD LOGOUT METHOD
//********************************************

    public boolean addUser(User user){
        try{
            userDao.addUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> displayUser() {
        List<User> users = new ArrayList<>();
        try {
            users = userDao.displayUser();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return users;
        }
    }

    public boolean updateUser(User user) {
        try{
            userDao.updateUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User user) {
        try{
            userDao.deleteUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
