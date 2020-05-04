package com.ex.web;

import com.ex.data.AccountSQLDatabase;
import com.ex.data.GenericDAO;
import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * CreateAccServlet Created By:Paityn Maynard on May 1,2020
 */
public class CreateAccServlet extends HttpServlet {

    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
            "postgres","revature","project1");
    GenericDAO<Account,String> accounts = new AccountSQLDatabase(connectionUtils);
    Account account = new Account();
    Boolean rowCount;
    String htmlResponse, password, confpassword, email;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        password = request.getParameter("password");
        confpassword = request.getParameter("confirmpassword");
        if(password != confpassword)
        {
            htmlResponse ="<html><h2>Passwords Do Not Match</h2></html>";
        }
        else {
            email = request.getParameter("email");
            if (accounts.findByID(email) == null) {
                htmlResponse = "<html><h2>Account Already Exists for" + email + "</h2></html>";
            } else {
                account.setName(request.getParameter("name"));
                account.setEmail(email);
                account.setPassword(password);

                rowCount = accounts.add(account);

                if (rowCount) {
                    htmlResponse = "<html>"
                                 + "<head><title>Creation Confirmation</title>"
                                 + "<link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>"
                                 + "<body> <h1 id=\"welcome\">Revature Pet Store</h1>"
                                 + "<h2>Successful creation</h2>"
                                 + "</body></html>";
                } else {
                    htmlResponse = "<html>"
                                 + "<head><title>Creation Confirmation</title>"
                                 + "<link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>"
                                 + "<body> <h1 id=\"welcome\">Revature Pet Store</h1>"
                                 +"<h2>Unsuccessful Creation</h2>"
                                 +"</body></html>";
                }
            }
        }
        out.println(htmlResponse);
    }
}

