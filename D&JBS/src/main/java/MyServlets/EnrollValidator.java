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
 * this is the server that check all validations for the enrollment  class
 * it basically control the field at the enrollment point of view
 *
 */

public class EnrollValidator extends HttpServlet {

    @Override
    public void init() throws ServletException { /**  this is being call only once through the entire call*/
       // super.init();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);

        /**
         * We set the type of the content that expected to send response
         *
         */
        resp.setContentType("text/html");

        /** we assign an array that will add all of the message or error find to all the inputs filed **/

        ArrayList array = new ArrayList();
        RequestDispatcher dispatcher;

        PrintWriter out = resp.getWriter();


        String card, social;

        card = req.getParameter("card");
        social = req.getParameter("social");

        if(social.length() == 0 || social.equals("") ) {
            array.add("Social Security can not be Empty");
        }
        if(social.matches("[A-Za-z]")){
            array.add("Social Security must be Number");


        }

        if(social.length() < 9){
            array.add("Social number must be valid of 9 Digit");


        }

        if(card.length() == 0 || card.equals("") ) {
            array.add("Account number can not be Empty");
        }
        if(card.matches("[a-zA-Z]")){
            array.add("Account number must be Number");


        }
        if(card.length() < 16){
            array.add("Account number must be valid of 9 Digit");


        }


        /** after checking for all inputs now we check if the array size is zero which will mean that users inputs are errors free **/

//        out.println(array);

        // this this where I check that if all inputs are correct
        if(array.size() !=0){

            out.println("<p class='alert alert-danger errorEnroll'>" + array + "</p>");
            dispatcher = req.getRequestDispatcher("enrollForm.html");
            dispatcher.include(req,resp);
        }

        /** else mean that no such errors found then  we can go or process databases **/

        else // Now they are correct I can call Database to process
        {




            // Calling the setters and getters Class
            AccountHolderPerSonalInfo  info = new AccountHolderPerSonalInfo();
            DAO database =new ManageDAO();

//            info.setSSN(social);
//            info.setAccountNumber(card);

            dispatcher = req.getRequestDispatcher("nextEnroll.html");
            dispatcher.include(req,resp);


//            try {
//              //  database.SelectFromRegisterTable();
//                out.println("database.getAllTablesForAccountHolder() "+" Test :" );

//                if(database.getAllTablesForAccountHolder().get(7).toString() == social)
//                {
//                    dispatcher = req.getRequestDispatcher("nextEnroll.html");
//
//                }else
//                {
//                    String mess = "Sorry We Do not have this Social Security in our Record";
//                    out.println("<p class='alert alert-danger errorEnroll'>" + mess + "</p>");
//                    dispatcher = req.getRequestDispatcher("enrollForm.html");
//                }
//                dispatcher.include(req,resp);


//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }





        }



    }
}
