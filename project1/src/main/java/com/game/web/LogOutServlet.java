package com.game.web;

import com.game.service.accountservices.AccountDetailService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            session.removeAttribute("username");
            RequestDispatcher rs = req.getRequestDispatcher("index.html");
            rs.forward(req, resp);
            ((AccountDetailService)getServletContext().getAttribute("accountDetailService")).logOff(username);
        }
    }
}
