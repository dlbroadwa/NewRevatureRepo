package com.ex.web;

import com.ex.models.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ManagerCreateAccServlet Created By:Paityn Maynard on May 6,2020
 */

public class ManagerCreateAccServlet extends HttpServlet {//Start of ManagerCreateAccServlet
//Instance Variables
    String job,levelOfEmployee;
    HttpSession session;
//Methods
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{//Start of doGet method
        session = request.getSession(false);
        levelOfEmployee = (String) session.getAttribute("levelOfEmployee");
        job = request.getParameter("job");
        if(levelOfEmployee.equals("manager")) {//Start of if statement
            session.setAttribute("job", job);
            response.sendRedirect("add_account.html");
        }//End of if statement
        else if(levelOfEmployee.equals("employee")&& job.equals("customer")){// Start of else if statement
            response.sendRedirect("add_account.html");
        }//End of else if statement
        else{//Start of else statement
            response.sendRedirect("pet_store_portal.html");
        }//End of else statement
    }//End of doGet method

}//End of ManagerCreateAccServlet
