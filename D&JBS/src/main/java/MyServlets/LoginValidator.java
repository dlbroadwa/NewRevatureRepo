package MyServlets;

import Controler.AccountHolderPerSonalInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * this is validation servlets that will validate  all login form which locate at the index page
 * at the main page we call it landing page it is where that everything start
 *
 */

public class LoginValidator extends HttpServlet {
    @Override
    public void init() throws ServletException {
        //super.init();
        AccountHolderPerSonalInfo info;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);


        resp.setContentType("text/html");

        RequestDispatcher dispatcher ;

        ArrayList array = new ArrayList();

        PrintWriter out = resp.getWriter();

/** again above is the variables setup do be used
 *
 * the battom is the checking validation for inputs
 */
        String email = req.getParameter("email").trim();
        String pass = req.getParameter("password").trim();

        if(email.length() == 0 || email.equals("")){

            array.add("Email Can not be Empty");
        }
        if(!email.matches("[\\w-]+@([\\w-]+\\.)+[\\w-]+")){

            array.add("Email Format id Incorrect");

        }

        if (pass.length() <= 5){

            array.add("Password must have at least 6 Character");

        }

        /** we check again if the array is empty  if not we will be forward to database
         *
         */
// this this where I check that if all inputs are correct
       if(array.size() != 0){

           out.println("<p class='alert alert-danger error' >"+ array + "</p>");
             dispatcher = req.getRequestDispatcher("index.html");
           dispatcher.include(req,resp);

        }else{ // Now they are correct I can call Database to process

           out.println("<p class='alert alert-Success error' >"+ "Welcome " + "</p>");
//            System.out.println("all Perfect" + array.size());
//            out.println("nope" + array + "email :"+ email +"password :" + pass);


        }


       out.close();
    }
}
