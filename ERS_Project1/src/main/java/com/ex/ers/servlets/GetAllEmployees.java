package com.ex.ers.servlets;

import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.services.PersonService;
import com.ex.ers.services.ReimbursementService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetAllEmployees extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService service = new PersonService();
        HttpSession session = req.getSession();
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json;charset=UTF-8");
        List<Person> all;
        List<Person> emps = null;

        all = service.getAllEmployees();
        for(Person tmp : all){
            if(tmp.isManager() !=1){
                emps.add(tmp);
            }
        }
        String output = new Gson().toJson(emps);
        out.print(output);
        resp.sendRedirect("viewAllEmployees.html");
    }
}
