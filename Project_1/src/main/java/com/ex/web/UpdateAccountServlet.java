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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateAccountServlet extends HttpServlet {//Start of UpdateAccountServlet
//Instance Variables
    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
        "postgres","revature","project1");
    GenericDAO<Account,String> accounts = new AccountSQLDatabase(connectionUtils);
    Account account = new Account();
    String password,email;
    HttpSession session;
    StringBuilder httpResponse;

//Methods
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        httpResponse = new StringBuilder();
        session = request.getSession(false);

        httpResponse.append("<html><head><title>Password Change</title> <link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>"
                            +"<body><h2>");
        if(session!=null){
            password= request.getParameter("password");
            email =(String) session.getAttribute("username");
            account=accounts.findByID(email);
            account.setPassword(password);
            accounts.update(email,account);
            httpResponse.append("Update complete <a class=\"button\" href=\"index.html\">Home Page</a>");
        }
        else{
            response.sendRedirect("index.html");
        }
        out.println(httpResponse);
        out.flush();

    }
}
