package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.UserDAO;
import com.Project0.util.CustReader;

public class LoginSuccess implements Screen {
    @Override
    public Screen doScreen(App app) {
        UserDAO userDAO = app.getUserDao();
        try{
           userDAO.loginUser(app.getUsername(), app.getPassword(), app);
            System.out.println("LOGIN SUCCESS!!!");
            //Move to the next screen - OPTIONS page 1
            return new MainOptions();
        } catch (Exception e) {
            System.out.println("ERROR LOGGING IN - INVALID CREDENTIALS");
            e.printStackTrace();
            return new Login();
        }
    }
}
