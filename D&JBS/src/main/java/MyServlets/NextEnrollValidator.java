package MyServlets;

import Controler.AccountHolderPerSonalInfo;
import Services.DAO;
import Services.ManageDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * this is the enrollment after confirmation when users click to enroll button from the index page
 * it will be re-confirm  then send the users to create emails and passwords
 *
 */
public class NextEnrollValidator extends HttpServlet {

    @Override
    public void init() throws ServletException {
//        super.init();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html");

        RequestDispatcher dispatcher ;

        ArrayList array = new ArrayList();

        PrintWriter out = resp.getWriter();


        String email = req.getParameter("email").trim();
        String pass = req.getParameter("password").trim();
        String ConfirmPass = req.getParameter("confirm-password").trim();

        if(email.length() == 0 || email.equals("")){

            array.add("Email Can not be Empty");
        }
        if(!email.matches("[\\w-]+@([\\w-]+\\.)+[\\w-]+")){

            array.add("Email Format id Incorrect");

        }

        if (pass.length() <= 5){

            array.add("Password must have at least 6 Character");

        }
//        if(pass != ConfirmPass){
//            array.add("Password is NOT match");
//
//
//
//        }


        // this this where I check that if all inputs are correct
        if(array.size() !=0){

            out.println("<p class='alert alert-danger errorEnrollNext'>" + array + "</p>");
            dispatcher = req.getRequestDispatcher("nextEnroll.html");
            dispatcher.include(req,resp);
        }
        else // Now they are correct I can call Database to process
        {


/** this where we handle database if array variable is free of errors
 *
 * if not we will not let them go to any further page until they correct the field that complain for errors
 */

            // Calling the setters and getters Class
            AccountHolderPerSonalInfo  info = new AccountHolderPerSonalInfo();
            DAO database = new ManageDAO();
//
            info.setPassword(pass);
            info.setEmail(email);
//            try {
//               // info.setRegisterID(database.getRegisterId());
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
            try {
                database.InsertToLoginTable(info);
                String Success = " You are SUCCESSFULLY Enrol to our Online  System";
                out.println("<p class='alert alert-success errorEnrollNext'>" + Success + "</p>");
                dispatcher = req.getRequestDispatcher("index.html");
                dispatcher.include(req,resp);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

//

        }


    }
}
