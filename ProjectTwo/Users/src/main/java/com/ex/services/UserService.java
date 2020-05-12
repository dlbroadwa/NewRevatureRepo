package com.ex.services;


import com.ex.dao.UserDAO;
import com.ex.dao.UserDAOImpl_PGR;
import com.ex.model.User;

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

    public User loginUser(String username, String password) {
        User user = null;
        try {
            user = userDao.loginUser(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return user;
        }
    }

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
