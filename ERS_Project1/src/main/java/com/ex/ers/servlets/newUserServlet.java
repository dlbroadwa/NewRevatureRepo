package com.ex.ers.servlets;

import com.ex.ers.models.Person;
import com.ex.ers.services.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newUserServlet")
public class newUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService service = new PersonService();
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String address = req.getParameter("address");
        String jobtitle = req.getParameter("jobtitle");
        String username = req.getParameter("username");
        String pw = req.getParameter("pw");

//        int status = 0;
        service.saveNewUser(fname,lname,address,jobtitle,username,pw);
        resp.sendRedirect("menu.html");
//        if (status == 0){  //username already in use
//            //insert an alert telling them the username is taken
//            resp.sendRedirect("index.html");
//        } else{
//            //insert an alert welcoming the new user
//            resp.sendRedirect("menu.html");
//        }
    }
}
