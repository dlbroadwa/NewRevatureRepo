package com.ex.web;

import javax.servlet.http.*;
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
    private Cookie getLoginCookie(Cookie[] cookies) {
        for (Cookie c: cookies) {
            if (c.getName().equals("login_user"))
                return c;
        }
        return null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {//Start of doGet method
        httpResponse=new StringBuilder();
        PrintWriter out = response.getWriter();

        Cookie login = getLoginCookie(request.getCookies());
        if (login != null) { //Start of if statement
            login.setMaxAge(0);
            login.setValue("");
            // This has the unfortunate side effect of clearing out your shopping cart...
            request.getSession().invalidate();
            httpResponse.append("<html><head><title>Logged Out</title><link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>"
                               +"<body> <h1 id=\"welcome\">Revature Pet Store</h1>"
                                +"<h2 id=\"mainh2\">You Are Logged Out</h2><a class=\"button\" href=\"sessionCheck\">Login</a>"
                                +"<a class=\"button\" href=\"index.html\">Back to Home Page</a>");
            out.println(httpResponse);
            out.flush();
        }//End of if statement
        else {//Start of else statement
            response.sendRedirect("index.html");
        }//End of else statement
    }//End of doGet method
}//End of Logout Servlet Class
