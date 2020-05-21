package com.ex.servlets;

import com.ex.model.User;
import com.ex.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class UserLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

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

        //cookies for the client side rendering
        Cookie c1 = new Cookie("userName", user.getFirstname() + " " + user.getLastname());
        c1.setMaxAge(60*60);

        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.addCookie(c1);
        resp.getWriter().write(json);
        System.out.println("UserLogin::COOKIE - " + c1.toString());

        resp.sendRedirect(req.getContextPath() + "/myhome.html");

    }
}
