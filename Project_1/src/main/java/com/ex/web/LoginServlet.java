package com.ex.web;

import com.ex.data.AccountSQLDatabase;
import com.ex.data.GenericDAO;
import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * LoginServlet Created By:Paityn Maynard on May 1,2020
 */

public class LoginServlet extends HttpServlet {//Start of LoginServlet Class
//Instance Variables
    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
            "postgres","revature","project1");
    GenericDAO<Account,String> accounts = new AccountSQLDatabase(connectionUtils);
    Account account = new Account();
    StringBuilder httpResponse;

//Methods
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//Start of doPost Method
        httpResponse=new StringBuilder();
        httpResponse.append("<html><head><title>Invalid Login</title> <link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        account = accounts.findByID(username);
        if (account == null) {//Start of first if statement
            httpResponse.append( "<body><h1 id=\"welcome\">Revature Pet Store</h1>"
                         +"<h2>Account Not Found</h2>"
                         + "<a class=\"button\" href=\"login.html\">Login</a><br/><br/>"
                         + "<a class=\"button\" href=\"add_account.html\">Create Account</a></body></html>");
            out.println(httpResponse);
        }//End of first if statement
        else {//Start of first else statement
            if(account.getPassword().equals(password)) {//Start of second if statement
                response.sendRedirect("index.html");
            }//End of second if statement
            else {//Start of second else statement
                httpResponse.append("<body><h1 id=\"welcome\">Revature Pet Store</h1>"
                             + "<h2>Passwords Do Not Match</h2><a class=\"button\" href=\"login.html\">Login</a></body></html>");
                out.println(httpResponse);
            }//End of second else statement
        }//End of first else statement
    }//End of doPost Method
}//End of LoginServlet Class
