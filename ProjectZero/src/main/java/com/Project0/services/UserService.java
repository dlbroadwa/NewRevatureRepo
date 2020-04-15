package com.Project0.services;

import com.Project0.application.App;
import com.Project0.dao.UserDAO;
import com.Project0.model.User;

public class UserService {
    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public User svcLoginUser(User user, App app) {
        try {
            return this.dao.loginUser(user.getUsername(), user.getPassword(), app);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean svcChangeUserPassword(User user, String newHashedPassword, App app) {
        try {
            return this.dao.changeUserPassword(user, newHashedPassword, app);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
