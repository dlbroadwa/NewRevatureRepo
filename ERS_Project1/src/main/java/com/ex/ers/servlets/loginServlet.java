package com.ex.ers.servlets;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.app.ERSApp;
import com.ex.ers.models.Person;
import com.ex.ers.services.PersonService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PersonService service = new PersonService();
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json;charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //check username is legit
        boolean status = service.legitName(username);
        if (status){
            //it's a legit username
            Person check = service.loginPerson(username, password);
            if (check != null){
                // make and send json object
                String personJsonString = new Gson().toJson(check);
                session.setAttribute("seshUser",check.getId());
                out.print(personJsonString);
                boolean manager = check.isManager();
                if(manager){
                    //get all employees, send to manager menu
                    resp.sendRedirect("manager_homepage.html");
                }else {
                    resp.sendRedirect("employee_homepage.html");
                }


            }else{
                log("<span>The password doesn't match the username.</span>");
                resp.sendRedirect("loginNoWork.html");
            }
        } else {
            //not legit
            log("<span>We couldn't find an account with that username.</span>");
            resp.sendRedirect("index.html");
        }

        out.close();

    }
}