package com.ex.ers.servlets;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.app.ERSApp;
import com.ex.ers.models.Person;
import com.ex.ers.services.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService service = new PersonService();
        PrintWriter out = resp.getWriter();
        String username=req.getParameter("username");
        String password = req.getParameter("password");

        //check username is legit
        int status = service.legitName(username);
        if (status==1){
            //it's a legit username
            int success = service.loginPerson(username, password);
            if (success==1){
                resp.sendRedirect("menu.html");
            }else{
                out.print("The password doesn't match the username.");
                resp.sendRedirect("index.html");
            }
        } else {
            //not legit
            out.print("We couldn't find an account with that username.");
            resp.sendRedirect("index.html");
        }

        out.close();

    }
}
