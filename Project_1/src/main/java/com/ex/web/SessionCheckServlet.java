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

        if(session==null){
            response.sendRedirect("login.html");
        }
        else{
            username= (String) session.getAttribute("username");
            account=accounts.findByID(username);
            if(account.getEmployee()||account.getManager()){
                response.sendRedirect("pet_store_portal.html");
            }
            else {
                response.sendRedirect("index.html");
            }
        }

    }//End of doGet method
}//End of SessionCheckServlet
