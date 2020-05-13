package MyServlets;

import Controler.AccountHolderPerSonalInfo;
import Controler.AccountNumberGenerator;
import Services.DAO;
import Services.ManageDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * this servlet is the one that will control new users
 * it will collect all the users information to help us filed our database
 *
 */


public class RegisterValidator extends HttpServlet {




    @Override
    public void init() throws ServletException {
       // super.init();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//doGet(req,resp);

       // super.doGet(req, resp);
        RequestDispatcher dispatcher;

        resp.setContentType("text/html");

        ArrayList array = new ArrayList();

        PrintWriter out = resp.getWriter();
//               out.println("register");

        String fname, lname, ssn, phone,StreetAddress, City, State, Zip, gender;

        fname = req.getParameter("firstname");
        lname = req.getParameter("lastname");
        ssn = req.getParameter("social");
        phone = req.getParameter("phone");
        StreetAddress = req.getParameter("streetAddress");
        City = req.getParameter("city");
        State = req.getParameter("state");
        Zip = req.getParameter("ZipCode");
        gender = req.getParameter("gender");
//
////String Symbol ="!@#$%^&*()_+}[]|/?><' ,.=+_ ";
//        array.add(lname);
//        array.add(fname);
//        array.add(ssn);
//        array.add(phone);
//        array.add(StreetAddress);
//        array.add(City);
//        array.add(State);
//        array.add(Zip);
//        array.add(gender);

//        out.println("register" + array);

        if(fname.length() == 0 || fname.equals("") ) {
    array.add("name Can not be Empty");
        }
        if(fname.matches("[A-Za-z]" )){

            array.add("Firstname can not have Number or Character");
        }

        if(lname.length() == 0 || lname.equals("") ) {
    array.add("Lastname can not be Empty");
        }
        if(fname.matches("[A-Za-z]" )){

            array.add("Lastname can not have Number or Character");
        }

        if(ssn.length() == 0 || ssn.equals("") ) {
            array.add("Social Security can not be Empty");
        }
        if(ssn.matches("[A-Za-z]")){
            array.add("Social number must be Number");


        }

        if(ssn.length() < 9){
            array.add("Social number must be valid of 9 Digit");


        }



        if(phone.length() == 0 || phone.equals("") ) {
            array.add("Phone can not be Empty");
        }

        if(phone.length() < 10) {
            array.add("Phone Must be 10 Digit number");
        }
        if(phone.matches("[a-zA-Z]")){
            array.add("Phone must be number");
        }

        if(StreetAddress.length() == 0 || StreetAddress.equals("") ) {
            array.add("Street Address can not be Empty");
        }

        if(City.length() == 0 || City.equals("") ) {
            array.add("City can not be Empty");
        }

        if(State.length() == 0 || State.equals("") ) {
            array.add("State can not be Empty");
        }

        if(Zip.length() == 0 || Zip.equals("") ) {
            array.add("Zip can not be Empty");
        }
        if(Zip.length() < 5  ) {
            array.add("Zip must be 5");
        }
        if(Zip.matches("[a-zA-Z]")){
            array.add("Zipcode must be number");
        }
//        if(gender.) {
//            array.add("Gender must be Selected");
//        }



// this this where I check that if all inputs are correct
            if(array.size() !=0){

                System.out.println("Something at Array Size");
                out.println("<p class='alert alert-danger errors'>" + array + "</p>");
                dispatcher = req.getRequestDispatcher("register.html");
                dispatcher.include(req,resp);


            }
            else // Now they are correct I can call Database to process
                {

/** here we create object from the DAO manager that will help us find which class that need to be called **/

                    ManageDAO  database =new ManageDAO();
                    AccountNumberGenerator random = new AccountNumberGenerator();







//                    database.getTheConnection();
//                    try {
//                        database.databaseConnection();
//                        out.println( database.getTheConnection());
//                        System.out.println(database.getTheConnection());
//
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                        System.out.println(e.getMessage());
//                        out.println(e.getMessage());
//                    }

//    Date  mytime = (Date) new java.util.Date();


/** this object of the AccountHolderPersonnalInfo will create all the setters
 *
 * that will hell database to access of the getters another to save to our database
 */
                    // Calling the setters and getters Class
                    AccountHolderPerSonalInfo  info = new AccountHolderPerSonalInfo();


                info.setFirstName(fname);
              info.setLastname(lname);
              info.setStreetAddress(StreetAddress);
              info.setCity(City);
              info.setState(State);
              info.setZipCode(Zip);
              info.setSSN(ssn);
              info.setGender(gender);
              info.setPhoneNumber(phone);
              info.setAccountNumber(random.accountGenerator());
              info.setDateOpenningAccount(database.getTimer());

                    try {

                        database.InsertToRegisterTable(info); // Inserting the data;
        String Success = " You are SUCCESSFULLY Registered to our System";
                        out.println("<p class='alert alert-success errors'>" + Success + "</p>");
                        dispatcher = req.getRequestDispatcher("index.html");
                        dispatcher.include(req,resp);


                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }

    }
}
