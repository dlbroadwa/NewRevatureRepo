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
            httpResponse.append("<head><link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\n" +
                    "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script></head>"
                                +"<body><ul class=\"list-group list-group-horizontal\">");

        accountList = accounts.findAll();
        if(accountList!=null) {//Start of first if statement
            for (Account a:accountList){//Start of for loop
                name = a.getName();
                email = a.getEmail();
                isEmployee = a.getEmployee();
                isManager = a.getManager();
                httpResponse.append("<li class=\"list-group-item\">Name: " + name + "<br/>Email: " + email + "<br/>");
                if (isEmployee) {//Start of second if statement
                    httpResponse.append("Employee</li>");
                }//End of second if statement
                if (isManager) {//Start of third if statement
                    httpResponse.append("Manager</li>");
                }//End of third if statement
            }//End of for loop
            httpResponse.append("</ul></body>");
            out.println(httpResponse);
        }//End of first if Statement
        else {//Start of first else statement
            out.println("Empty");
        }//End of first else statement

    }//End of doGet Method
}
