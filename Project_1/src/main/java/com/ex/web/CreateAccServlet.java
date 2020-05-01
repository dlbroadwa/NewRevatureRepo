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

@WebServlet("/createAccount")
public class CreateAccServlet extends HttpServlet {

    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
            "postgres","revature","project1");
    GenericDAO<Account,String> accounts = new AccountSQLDatabase(connectionUtils);
    Account account = new Account();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        account.setName(request.getParameter("name"));
        account.setEmail(request.getParameter("email"));
        account.setPassword(request.getParameter("password"));

        accounts.add(account);

        String htmlResponse = "<html>";
        htmlResponse += "<h2>Successful creation</h2>";
        htmlResponse += "</html>";
        out.println(htmlResponse);
//        if(username!= null && username==account.getEmail()){
//            String password = request.getParameter("password");
//            if(password==account.getPassword()){
//                out.print("Successful Login");
//            }
//        }
    }
}

