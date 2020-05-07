package com.ex.ers.newServlets;

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

@WebServlet("/loginServletNEW")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService service = new PersonService();
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json;charset=UTF-8");

        String username = req.getParameter("username");
        System.out.println(username);
        String password = req.getParameter("password");
        System.out.println(password);
        String [] info = {username, password, "index.html", "true"};

        //check username is legit
        boolean status = false;
        try{
            status = service.legitName(username);
        } catch (Exception exception){
            System.out.println("Invalid credentials on LoginServlet");
        }

        if (status){
            //it's a legit username
            Person check = service.loginPerson(username, password);
            if (check != null){
                // make and send json object
                String personJsonString = new Gson().toJson(check);
                //out.print(personJsonString);
                boolean manager = check.isManager();
                info[3] = "false";
                if(manager){
                    //get all employees, send to manager menu
                    info[2] = "manager_homepage.html";
                    resp.getOutputStream().print(new Gson().toJson(info));
                    //resp.sendRedirect("index.html");
                }else {
                    info[2] = "employee_homepage.html";
                    resp.getOutputStream().print(new Gson().toJson(info));
                    //resp.sendRedirect("index.html");
                }
            }else{
                log("<span>Invalid User Credentials</span>");
                info[3] = "true";
                resp.getOutputStream().print(new Gson().toJson(info));
                //resp.sendRedirect("index.html");
            }
        } else {
            //not legit
            log("<span>Invalid User Credentials</span>");
            info[3] = "true";
            resp.getOutputStream().print(new Gson().toJson(info));
            //resp.sendRedirect("index.html");
        }
        out.close();
    }
}
