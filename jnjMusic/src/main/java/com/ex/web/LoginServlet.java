package com.ex.web;

import data.UserRepo;
import models.Users;
import utils.ConnectionUtils;
import utils.PostgresConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    //Called for logging into the DB
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = new UserRepo(new PostgresConnectionUtil()).findById(req.getParameter("email"));
        if (user.getPassword() == req.getParameter("password"))
        {
            resp.getWriter().write("Hello There "+user.getF_name()+" "+user.getL_Name() +"!");
            resp.setStatus(200);
            resp.setContentType("text/plain");
        }
        else if (user == null)
        {
            resp.setStatus(400);
        }
        else resp.setStatus(404);
    }
    //Called for registering a new user
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    //Called to Update customer Phone number
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
    //Not used, we will not remove customer data
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
