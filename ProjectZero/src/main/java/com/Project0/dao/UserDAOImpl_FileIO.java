package com.Project0.dao;

import com.Project0.application.App;
import com.Project0.model.User;
import com.Project0.util.CustReader;
import com.Project0.util.CustWriter;

public class UserDAOImpl_FileIO implements UserDAO{
    @Override
    public User loginUser(String username, String password, App app) throws Exception {
        CustReader reader = new CustReader();
        User thisUser = reader.readUserLoginRequest("src/main/resources/Users", username, password);
        if(thisUser == null)
            throw new Exception("UNABLE TO LOGIN USER");
        app.setUser(thisUser);
        return thisUser;
    }

    @Override
    public void changeUserPassword(User user, String newHashedPassword, App app) throws Exception {
        CustWriter writer = new CustWriter();
        try {
            writer.updateUserPassword(user, newHashedPassword);
            app.setPassword(newHashedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
