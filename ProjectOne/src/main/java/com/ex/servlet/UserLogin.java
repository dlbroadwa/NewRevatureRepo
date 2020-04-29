package com.ex.servlet;

import com.ex.dao.UserDAO;
import com.ex.dao.UserDAOImpl_PGR;
import com.ex.model.User;
import com.ex.service.ConfigVarsService;
import com.ex.service.ConnectionService;
import com.ex.service.PostgreSQLConnection;
import com.ex.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * This servlet is the login response servlet when a user tries to login to the header section login
 * This class initiates the HTTPSession API - giving birth to the 'loggedUser' session object(User).  It
 * also produces a cookie for the username
 */
public class UserLogin extends HttpServlet {
    ConfigVarsService configService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //when service is called - load config file to get any new saved config vars
        configService = new ConfigVarsService();

        super.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        //Setup the session to store session information for later use
        HttpSession session = req.getSession();

        //Service provides us ability to hash a password input from the login form
        UserService service = new UserService();

        String rawPass = req.getParameter("userPassword");
//        System.out.printf("RAW PASS: %s", rawPass);
        String hashedPass = service.hashPassword(rawPass);
        String inUser = req.getParameter("userName");
//        System.out.printf("USERNAME: %s", inUser);
//        System.out.printf("PASSWORD: %s", hashedPass);

        /* SESSION/COOKIE VARIABLES */
        int inActiveDuration = 5;
        int cookieDuration = 30;
        User user = null;
        try{
            user = service.loginUser(inUser, hashedPass);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UserLogin - ERROR LOGGING IN");
        }

        //This will only run if we successfully login
        if(user != null) {
            session.setAttribute("loggedUser", user);
            try {
                inActiveDuration = Integer.parseInt(configService.getProperty("inactivityTimeoutMinutes"));
                cookieDuration = Integer.parseInt(configService.getProperty("cookieDurationMinutes"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            session.setMaxInactiveInterval(inActiveDuration *= 60);   //value is based on seconds - so multiply to meet minutes
            Cookie userName = new Cookie("username", inUser);
            userName.setMaxAge(cookieDuration *= 60);
            resp.addCookie(userName);
            System.out.println("UserLogin - SUCCESSFULLY LOGGED IN!!");
        }
        resp.sendRedirect("index.html");
    }
}
