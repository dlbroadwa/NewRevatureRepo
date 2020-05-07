package web;

import app.ShelterApplication;
import models.user.Admin;
import models.user.Customer;
import models.user.Employee;
import models.user.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *  Project 1:<br>
 * <br>
 *  The UserServlet class serves as a Java Servlet that holds an extension of PetService while running the application
 *    within a Tomcat container or within a Docker image.
 *  This will allow UserService operations to be performed as long as the Tomcat/Docker service is enabled, which
 *    includes CRUD operations on the pet table. Different actions are determined through the detection of certain
 *    keywords within URLs.
 *
 *  <br> <br>
 *  Created: <br>
 *     02 May 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     02 May 2020, Barthelemy Martinon,    Created class.
 *     										  Prototyped init, service, destroy, doGet, doPost, createAction, readAction,
 *     										    updateAction and deleteAction.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 02 May 2020
 */
public class UserServlet extends HttpServlet {

    // Instance Variables
    private UserService userService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
        System.out.println("Servicing UserServlet");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        /*
         * destroy -- gets called when the server needs it to be.
         *           most likely at server shutdown.
         * */
        System.out.println("Destroy UserServlet");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        /*
         * init -- beginning of the servlet lifecycle
         *         it runs once, if the servlet has never been initialize
         *         when the first request to a matching url pattern is made.
         *         You can preload servlet with <load-on-startup> in the web.xml.
         * */
        System.out.println("Init UserServlet");
        ShelterApplication app = ShelterApplication.getInstance();
        userService = app.getUserServ();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String action = req.getServletPath();
        String URL = req.getRequestURL().toString();
        String action = null;

        if ( URL.contains("/add") ) {
            action = "/add";
        } else if ( URL.contains("/searchID") ) {
            action = "/searchID";
        } else if ( URL.contains("/update") ) {
            action = "/update";
        } else if ( URL.contains("/delete") ) {
            action = "/delete";
        }

        System.out.println(action);

        try {
            switch (action) {
                case "/add":
                    doPost(req, resp);
                    break;
                case "/searchID":
                    readAction(req, resp);
                    break;
                case "/update":
                    doPost(req, resp);
                    break;
                case "/delete":
                    doPost(req, resp);
                    break;
                default:
                    System.err.println("Default reached while running GET, meaning bad path.");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String action = req.getServletPath();
        String URL = req.getRequestURL().toString();
        String action = null;

        if ( URL.contains("/add") ) {
            action = "/add";
        } else if ( URL.contains("/searchID") ) {
            action = "/searchID";
        } else if ( URL.contains("/update") ) {
            action = "/update";
        } else if ( URL.contains("/delete") ) {
            action = "/delete";
        }

        try {
            switch (action) {
                case "/add":
                    createAction(req, resp);
                    break;
                case "/searchID":
                    doGet(req, resp);
                    break;
                case "/update":
                    updateAction(req, resp);
                    break;
                case "/delete":
                    deleteAction(req, resp);
                    break;
                default:
                    System.err.println("Default reached while running POST, meaning bad path.");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // Switch Statement Case Methods

    private void createAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String usertype = req.getParameter("usertypeC");
        String fname = req.getParameter("fnameC");
        String lname = req.getParameter("lnameC");
        String userid = req.getParameter("useridC");
        String username = req.getParameter("usernameC");
        String password = req.getParameter("passwordC");

        User newUser = null;
        if (usertype.equals("customer")) {
            newUser = new Customer(fname,lname,Integer.parseInt(userid),username,password);
        } else if (usertype.equals("employee")) {
            newUser = new Employee(fname,lname,Integer.parseInt(userid),username,password);
        } else if (usertype.equals("admin")) {
            newUser = new Admin(fname,lname,Integer.parseInt(userid),username,password);
        }
        User result = userService.addNewUser(newUser);

        if(result != null) {
            resp.getWriter().write("Created : " + result.printBaseInfo());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No User was added, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }

    private void readAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String userid = req.getParameter("useridR1");
        User result = userService.searchByID(Integer.parseInt(userid));

        if(result != null) {
            resp.getWriter().write("Search Result: " + result.printBaseInfo());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No Results found, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }

    private void updateAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String useridin = req.getParameter("useridUIn");
        String usernamein = req.getParameter("usernameUIn");
        String passwordin = req.getParameter("passwordUIn");

        String fname = req.getParameter("fnameU");
        String lname = req.getParameter("lnameU");
        String username = req.getParameter("usernameU");
        String password = req.getParameter("passwordU");

        User target = userService.searchByID(Integer.parseInt(useridin));
        User upToDateUser = null;
        User result = null;
        if (target != null) {
            if (target.userAuth(usernamein, passwordin) == true) {
                if (target.getUserType().equals("admin")) {
                    upToDateUser = new Admin(fname, lname, Integer.parseInt(useridin), username, password);
                    result = userService.updateUser(upToDateUser, target.getID());
                } else if (target.getUserType().equals("employee")) {
                    upToDateUser = new Employee(fname, lname, Integer.parseInt(useridin), username, password);
                    result = userService.updateUser(upToDateUser, target.getID());
                } else if (target.getUserType().equals("customer")) {
                    upToDateUser = new Customer(fname, lname, Integer.parseInt(useridin), username, password);
                    result = userService.updateUser(upToDateUser, target.getID());
                } else if (target == null) {
                    // Do nothing
                }
            }
        }

        if(result != null) {
            resp.getWriter().write("Update Result: " + result.printBaseInfo());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No User was found with matching ID, Incorrect Username and/or Password, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }

    private void deleteAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String userid = req.getParameter("useridD");
        User result = userService.removeUser(Integer.parseInt(userid));

        if(result != null) {
            resp.getWriter().write("Deleted : " + result.printBaseInfo());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No User with Matching ID found, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }
}