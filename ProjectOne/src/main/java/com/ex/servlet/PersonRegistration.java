package com.ex.servlet;

import com.ex.model.Person;
import com.ex.model.PhoneCarrier;
import com.ex.model.Team;
import com.ex.model.User;
import com.ex.service.PersonService;
import com.ex.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonRegistration extends HttpServlet {
    public PersonRegistration(){

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        System.out.println("Do post called");
        String name = req.getParameter("firstName") + " " + req.getParameter("lastName");
        String phone = req.getParameter("phoneNumber");
        String emergencyPhone = req.getParameter("emergencyPhone");
        PhoneCarrier carrier = null;
        Boolean allowSms = null;
        Team team = null;





        Person thisPerson = new Person(name, phone, emergencyPhone, carrier, allowSms, team);
        PersonService service = new PersonService();
        boolean success = service.addPerson(thisPerson);
        if(success){
            //logic to return to index.html needs to be added
            //System.out.println("Add user successful");
            resp.sendRedirect("index.html");

        }else{
            //this is the error path
            //System.out.println("User not added");
        }
    }
}
