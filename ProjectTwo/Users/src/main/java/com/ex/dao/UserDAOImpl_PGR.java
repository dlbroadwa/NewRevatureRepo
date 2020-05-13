package com.ex.dao;

import com.ex.model.User;

import java.sql.Connection;
import java.util.List;

public class UserDAOImpl_PGR implements UserDAO {


    @Override
    public User loginUser(String username, String password) throws Exception {
        return null;
    }

    @Override
    public void logoutUser() {
        Connection connection = null;

        
    }

    @Override
    public void addUser(User user) throws Exception {

    }

    @Override
    public List<User> displayUser() {
        return null;
    }

    @Override
    public void updateUser(User user) throws Exception {

    }

    @Override
    public void deleteUser(User user) throws Exception {

    }
}
