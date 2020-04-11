package com.Project0.dao;

import com.Project0.application.App;

public interface UserDAO {
    //login with the username/pass
    public void loginUser(String username, String password, App app) throws Exception;
}
