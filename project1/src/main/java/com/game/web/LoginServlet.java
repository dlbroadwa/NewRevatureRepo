package com.game.web;

import com.game.service.accountservices.AccountDetailService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    AccountDetailService accountDetailService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        accountDetailService = (AccountDetailService) context.getAttribute("accountDetailService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String destPage = "signup.html";

        if(accountDetailService.checkCredentials(username, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            destPage = "profile.html";
            RequestDispatcher rs = req.getRequestDispatcher("Welcome" + username);
            rs.forward(req, resp);
        } else {
            resp.getWriter().write("Username or Password is incorrect. Please try again");
        }
        RequestDispatcher rs = req.getRequestDispatcher(destPage);
        rs.include(req, resp);
    }
}