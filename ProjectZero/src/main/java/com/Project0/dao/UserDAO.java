package com.Project0.dao;

import com.Project0.application.App;
import com.Project0.model.User;

public interface UserDAO {
    //login with the username/pass
    public User loginUser(String username, String password, App app) throws Exception;

    public void changeUserPassword(User user, String newHashedPassword, App app) throws Exception;
}
