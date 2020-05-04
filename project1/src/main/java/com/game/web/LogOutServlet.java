package com.game.web;

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
        HttpSession session = req. getSession(false);

        if (session != null) {
            session.removeAttribute("username");
            RequestDispatcher rs = req.getRequestDispatcher("signup.html");
            rs.forward(req, resp);
        }
    }
}
