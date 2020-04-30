package com.ex.servlet;

import com.ex.model.User;
import com.ex.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegistrationComplete extends HttpServlet {
    public UserRegistrationComplete() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String userName = req.getParameter("name");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");
        String email = req.getParameter("email");

        User thisUser = new User(userName, password, passwordRepeat, email);
        UserService service = new UserService();
        boolean success = service.addUser(thisUser);
        if(success){
            //logic to return to index.html needs to be added
            resp.sendRedirect("index.html");
        }else{
            //this is the error path
        }
    }
}
