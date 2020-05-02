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

public class LoginServlet extends HttpServlet {

    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
            "postgres","revature","project1");
    GenericDAO<Account,String> accounts = new AccountSQLDatabase(connectionUtils);
    Account account = new Account();
    String htmlResponse;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        account = accounts.findByID(username);
        if (accounts == null) {
            htmlResponse = "<html><h2>Account Not Found</h2></html>";
            out.println(htmlResponse);
        } else {
            if(account.getPassword() == password) {
                htmlResponse = "<html>";
                htmlResponse += "<head><title>Creation Confirmation</title>";
                htmlResponse += "<link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>";
                htmlResponse += "<body> <h1 id=\"welcome\">Revature Pet Store</h1>";
                htmlResponse += "<h2>Hello</h2>";
                htmlResponse += "</body></html>";
            }else {
                htmlResponse = "<html><h2>Passwords Do Not Match</h2></html>";
            }
            out.println(htmlResponse);
        }
    }
}
