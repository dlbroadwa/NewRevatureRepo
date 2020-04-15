package com.Project0.services;

import com.Project0.application.App;
import com.Project0.dao.UserDAO;
import com.Project0.model.User;

public class UserService {
    private UserDAO udao;

    public UserService(UserDAO dao) {
        this.udao = dao;
    }

    public User svcLoginUser(User user, App app) {
        try {
            User temp = new User();
            temp = this.udao.loginUser(user.getUsername(), user.getPassword(), app);
            app.setUser(temp);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Boolean svcChangeUserPassword(User user, String newHashedPassword, App app) {
        try {
            return this.udao.changeUserPassword(user, newHashedPassword, app);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
