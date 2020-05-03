package com.ex.ers.servlets;

import com.ex.ers.models.Person;
import com.ex.ers.services.PersonService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/newUserServlet")
public class newUserServlet extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService service = new PersonService();

        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String address = req.getParameter("address");
        String jobtitle = req.getParameter("jobtitle");
        String username = req.getParameter("username");
        String pw = req.getParameter("pw");

        // make and send json object
        resp.setContentType("application/json;charset=UTF-8");
        Person person = new Person();
        person = service.saveNewUser(fname,lname,address,jobtitle,username,pw);
        PrintWriter out = resp.getWriter();
        String personJsonString = this.gson.toJson(person);
        out.print(personJsonString);
        out.flush();
        resp.sendRedirect("menu.html");


        //        int status = 0;
//        if (status == 0){  //username already in use
//            //insert an alert telling them the username is taken
//            resp.sendRedirect("index.html");
//        } else{
//            //insert an alert welcoming the new user
//            resp.sendRedirect("menu.html");
//        }
    }
}
