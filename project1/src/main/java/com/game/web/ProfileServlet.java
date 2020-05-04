package com.game.web;

import com.game.service.accountservices.AccountDetailService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    AccountDetailService accountDetailService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        accountDetailService = (AccountDetailService) context.getAttribute("accountDetailService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("profile.html").include(req, resp);

        HttpSession session = req.getSession(false);
        if (session!=null) {
            String username = (String)session.getAttribute("username");
            resp.getWriter().write("Yo, " + username + "!");
            accountDetailService.findByID(username);
        } else {
            resp.getWriter().write("You haven't logged in yet =/");
            req.getRequestDispatcher("signup.html").include(req, resp);
        }
    }
}
