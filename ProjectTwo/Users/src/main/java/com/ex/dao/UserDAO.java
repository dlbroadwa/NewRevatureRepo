package com.ex.dao;


import com.ex.model.User;

import java.util.List;

/**
 * This is the UserDAO interface to be implemented amongst DAO classes.
 */
public interface UserDAO {
    public User loginUser(String email, String password) throws Exception;
    public void addUser(User user) throws Exception;
    public User displayUser(User user);
    public void updateUser(User user) throws Exception;
    public boolean disableUser(User user, boolean bIsDisabled) throws Exception;
}
