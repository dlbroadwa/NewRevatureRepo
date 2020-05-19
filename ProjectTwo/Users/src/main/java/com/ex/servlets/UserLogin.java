package com.ex.servlets;

import com.ex.model.User;
import com.ex.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPut(req, resp);

        HttpSession session = req.getSession();
        User user = new User();
        UserService uService = new UserService();
        String email = req.getParameter("email");
        String hashedPass = uService.hashPassword(req.getParameter("password"));
        user.setEmail(email);
        user.setPassword(hashedPass);

        user = uService.loginUser(user);
        session.setAttribute("LoggedInUser", user);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.getWriter().write(json);
        System.out.println("UserLogin::JSON RESPONSE - " + json);

    }
}
