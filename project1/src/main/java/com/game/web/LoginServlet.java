package com.game.web;

import com.game.service.accountservices.AccountDetailService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.game.system.PortalContextListener;

public class LoginServlet extends HttpServlet {
    AccountDetailService accountDetailService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String destPage = "signup.html";
//        Possible login/logout servlet to be added
//        LoginDao userDao = new UserDao();
        if(accountDetailService.checkCredentials(username, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            destPage = "portal.html";
            RequestDispatcher rs = req.getRequestDispatcher("Welocme");
            rs.forward(req, resp);
        } else {
            resp.getWriter().write("Username or Password is incorrect. Please try again");
        }
        RequestDispatcher rs = req.getRequestDispatcher(destPage);
        rs.include(req, resp);
    }
}
