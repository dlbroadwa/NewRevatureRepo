package com.ex.web;

import com.ex.data.AccountSQLDatabase;
import com.ex.data.GenericDAO;
import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Paityn Maynard on May 7,2020
 */
public class SessionCheckServlet extends HttpServlet {//Start of SessionCheckServlet
//Instance Variables

    //Methods
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//Start of doGet method
        /*Cookie loggedInUser = null;
        for (Cookie c: request.getCookies()) {
            if (c.getName().equals("login_user")) {
                loggedInUser = c;
                if (request.getSession().isNew()) {
                    refreshSession(request.getSession());
                }
                break;
            }
        }*/

        // Check for active login
        HttpSession session = request.getSession(false);
        Account user = null;
        if (session == null || (user = (Account)session.getAttribute("user")) == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Figure out where to redirect
        String redirectURL = request.getParameter("redirect");
        if (redirectURL == null) {
            if (user.getEmployee() || user.getManager())
                redirectURL = "pet_store_portal.html";
            else
                redirectURL = "index.html";
        }

        response.sendRedirect(redirectURL);
    }//End of doGet method
}//End of SessionCheckServlet
