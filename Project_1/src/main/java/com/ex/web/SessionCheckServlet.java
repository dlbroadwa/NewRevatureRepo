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

/**
 * Created by Paityn Maynard on May 7,2020
 */
public class SessionCheckServlet extends HttpServlet {//Start of SessionCheckServlet
//Instance Variables
    HttpSession session;
    String username;
    Account account;

//Methods
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//Start of doGet method
        DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
                "postgres","revature","project1");
        GenericDAO<Account,String> accounts = new AccountSQLDatabase(connectionUtils);

        session=request.getSession(false);

        if(session==null){//Start of first if statement
            response.sendRedirect("login.html");
        }//End of first if statement
        else{//Start of first else statement
            username= (String) session.getAttribute("username");
            account=accounts.findByID(username);
            if(account.getEmployee()||account.getManager()){//Start of second if statement
                response.sendRedirect("pet_store_portal.html");
            }//End of second if statement
            else {//Start of second else statement
                response.sendRedirect("index.html");
            }//End of second else statement
        }//End of first else statement

    }//End of doGet method
}//End of SessionCheckServlet
