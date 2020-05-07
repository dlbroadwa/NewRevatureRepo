package com.ex.web;

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
    String job;
    HttpSession session;
//Methods
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{//Start of doGet method
        session = request.getSession(false);
        job = request.getParameter("job");
        session.setAttribute("job",job);
        response.sendRedirect("add_account.html");
    }//End of doGet method

}//End of ManagerCreateAccServlet
