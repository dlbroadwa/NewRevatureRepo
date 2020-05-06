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
import java.util.ArrayList;
import java.util.List;

public class AllAccountsServlet extends HttpServlet {
//Instance Variables
    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
            "postgres","revature","project1");
    GenericDAO<Account,String> accounts = new AccountSQLDatabase(connectionUtils);
    List<Account> accountList;
    String name, email;
    boolean isEmployee, isManager;

//Methods
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//Start of doGet Method
        accountList = new ArrayList<>();
        PrintWriter out = response.getWriter();
        StringBuilder httpResponse = new StringBuilder();
            httpResponse.append("<body>");

        accountList = accounts.findAll();
        if(accountList!=null) {//Start of first if statement
            for (Account a:accountList){//Start of for loop
                name = a.getName();
                email = a.getEmail();
                isEmployee = a.getEmployee();
                isManager = a.getManager();
                httpResponse.append("<p>Name: " + name + "<br/>Email: " + email + "<br/>");
                if (isEmployee) {//Start of second if statement
                    httpResponse.append("Employee<br/>");
                }//End of second if statement
                if (isManager) {//Start of third if statement
                    httpResponse.append("Manager<br/>");
                }//End of third if statement
                httpResponse.append("</p>");
            }//End of for loop
            httpResponse.append("</body>");
            out.println(httpResponse);
        }//End of first if Statement
        else {//Start of first else statement
            out.println("Empty");
        }//End of first else statement

    }//End of doGet Method
}
