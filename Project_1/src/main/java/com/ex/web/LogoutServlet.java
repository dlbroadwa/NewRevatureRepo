package com.ex.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Paityn Maynard on May 7,2020
 */
public class LogoutServlet extends HttpServlet {//Start of LogoutServlet Class
//Instance Variables
    HttpSession session;
    StringBuilder httpResponse;
//Methods
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {//Start of doGet method
        httpResponse=new StringBuilder();
        PrintWriter out = response.getWriter();
        session=request.getSession(false);
        if(session!=null) {//Start of if statement
            httpResponse.append("<html><head><title>Creation Confirmation</title><link rel=\\\"stylesheet\\\" type=\\\"text/css\\\" href=\\\"webDesign.css\\\"></head>\"\n"
                               +"<body> <h1 id=\"welcome\">Revature Pet Store</h1>"
                                +"<h2 id=\"mainh2\">You Are Logged Out</h2><a class=\"button\" href=\"sessionCheck\">Login</a>");
            session.invalidate();
            out.println(httpResponse);
            out.flush();
        }//End of if statement
        else {//Start of else statement
            response.sendRedirect("index.html");
        }//End of else statement
    }//End of doGet method
}//End of Logout Servlet Class
