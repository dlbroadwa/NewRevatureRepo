package com.ex.dao;


import com.ex.model.User;

import java.util.List;

/**
 * This is the UserDAO interface to be implemented amongst DAO classes.
 */
public interface UserDAO {
    public User loginUser(String username, String password) throws Exception;
    public void addUser(User user) throws Exception;
    public List<User> displayUser();
    public void updateUser(User user) throws Exception;
    public void deleteUser(User user) throws Exception;
}
