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
/**
 * CreateAccServlet Created By:Paityn Maynard on May 1,2020
 */
public class CreateAccServlet extends HttpServlet {//Start of CreateAccServlet
//Instance Variables
    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
            "postgres","revature","project1");
    GenericDAO<Account,String> accounts = new AccountSQLDatabase(connectionUtils);
    Account account = new Account(),manager=new Account();
    Boolean rowCount;
    String password, confpassword, email, name,job;
    StringBuilder httpResponse;
    HttpSession session;

//Methods
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//Start of doPost Method
        session=request.getSession(false);

        //        if(session != null){
        //            manager=accounts.findByID((String) session.getAttribute("username"));
        //
        //        }else {
        //            manager.setManager(false);
        //        }

        httpResponse=new StringBuilder();
            httpResponse.append("<html><head><title>Creation Confirmation</title><link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>"
                                +"<body> <h1 id=\"welcome\">Revature Pet Store</h1>");
        PrintWriter out = response.getWriter();
        name = request.getParameter("name");
        email = request.getParameter("email");
        password = request.getParameter("password");
        confpassword = request.getParameter("confirmpassword");


        if(!password.equals(confpassword))//Start of first if statement
        {
            httpResponse.append("<h2>Passwords Do not Match"
                    +"<a class=\"button\" href=\"add_account.html\">Create Account</a></h2>"
                    + "</body></html>");
        }//End of first if statement

        else {//Start of first else statement
            account.setName(name);
            account.setEmail(email);
            account.setPassword(password);

            if(manager.getManager()){//Start of second if statement
                job = (String)session.getAttribute("job");

                if(job.equals("employee")){//Start of third if statement
                    account.setEmployee(true);
                }//End of third if statement

                else if(job.equals("manager")){//Start of first if else statement
                    account.setManager(true);
                }//End of first if else statement

            }//End of second if statement

            rowCount = accounts.add(account);

                if (rowCount) {//Start of third if statement
                    httpResponse.append("<h2>Successful creation</h2>"
                                 +"<a class=\"button\" href=\"login.html\">Login</a>"
                                 + "</body></html>");
                        out.println(httpResponse);
                    response.sendRedirect("index.html");
                }//End of third if else statement

                else {//Start of second else (paired with third if) statement
                    httpResponse.append("<h2>Unsuccessful Creation"
                                 +"<a class=\"button\" href=\"add_account.html\">Create Account</a></h2>"
                                 +"</body></html>");
                    out.println(httpResponse);
                }//End of second else statement
            }//End of first else statement
        out.flush();
    }//End of doPost Method
}//End of CreateAccServlet

