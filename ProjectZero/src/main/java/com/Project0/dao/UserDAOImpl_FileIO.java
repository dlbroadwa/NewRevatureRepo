package com.Project0.dao;

import com.Project0.application.App;
import com.Project0.model.User;
import com.Project0.util.CustReader;

public class UserDAOImpl_FileIO implements UserDAO{
    @Override
    public void loginUser(String username, String password, App app) throws Exception {
        CustReader reader = new CustReader();
        User thisUser = reader.readUserLoginRequest("src/main/resources/Users", username, password);
        if(thisUser == null)
            throw new Exception("UNABLE TO LOGIN USER");
        app.setUser(thisUser);
    }
}
