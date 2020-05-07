package com.game.web;

import com.game.service.accountservices.AccountDetailService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
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
//        String destPage = "index.html";
        if(accountDetailService.checkCredentials(username, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
//            destPage = "portal.html";
            RequestDispatcher rs = req.getRequestDispatcher("pages/portal.html");
            rs.forward(req, resp);
        } else {
            resp.getWriter().write("Username or Password is incorrect. Please try again");
            //resp.getWriter().write("Username or Password is incorrect. Please try again");
            RequestDispatcher rs = req.getRequestDispatcher("index.html");
            rs.include(req, resp);
        }
    }
}
