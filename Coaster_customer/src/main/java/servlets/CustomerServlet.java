package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.CustomerDAO;
import models.Customer;
import utils.PostgresConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  Project 1:<br>
 * <br>
 *  The CustomerServlet class serves as a Java Servlet that holds an extension of CustomerDAO while running the
 *    application within a Tomcat container or within a Docker image.
 *  This will allow CustomerDAO operations to be performed as long as the Tomcat/Docker service is enabled, which
 *    includes CRUD operations on the customers table. Different actions are determined through the detection of certain
 *    keywords within URLs.
 *
 *  <br> <br>
 *  Created: <br>
 *     13 May 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     13 May 2020, Barthelemy Martinon,    Created class.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 13 May 2020
 */
public class CustomerServlet extends HttpServlet {

    // Instance Variables
//    CustomerDAO customerDAO = new CustomerDAO(new PostgresConnectionUtil(
//            "jdbc:postgresql://revdemo.cmyaylobpmky.us-east-2.rds.amazonaws.com:5432/postgres", "voldemort",
//            "password"));
    CustomerDAO customerDAO = new CustomerDAO(new PostgresConnectionUtil());

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
        System.out.println("Servicing CustomerServlet");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        /*
         * destroy -- gets called when the server needs it to be.
         *           most likely at server shutdown.
         * */
        System.out.println("Destroy CustomerServlet");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        /*
         * init -- beginning of the servlet lifecycle
         *         it runs once, if the servlet has never been initialize
         *         when the first request to a matching url pattern is made.
         *         You can preload servlet with <load-on-startup> in the dev.web.xml.
         * */
        System.out.println("Init CustomerServlet");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        if (data.get("move").getAsString().equals("create")) {
            doPost(req,resp);
        } else if (data.get("move").getAsString().equals("read")) {
            doPost(req,resp);
        } else if (data.get("move").getAsString().equals("readall")) {
            doPost(req,resp);
        } else if (data.get("move").getAsString().equals("update")) {
            doPost(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        if (data.get("move").getAsString().equals("create")) {
            try {
                createAction(req,resp);
            } catch (Exception e) {
                System.err.println("Error reached while running POST.");
                Map<String, String> options = new LinkedHashMap<>();
                options.put("response", "Email Already Exists");
                resp.setStatus(200);
            }
        } else if (data.get("move").getAsString().equals("read")) {
            try {
                readAction(req,resp);
            } catch (Exception e) {
                System.err.println("Error reached while running GET.");
                Map<String, String> options = new LinkedHashMap<>();
                options.put("response", "No Results Found");
                resp.setStatus(200);
            }
        } else if (data.get("move").getAsString().equals("readall")) {
            try {
                readAllAction(req,resp);
            } catch (Exception e) {
                System.err.println("Error reached while running GET.");
                Map<String, String> options = new LinkedHashMap<>();
                options.put("response", "No Results Found");
                resp.setStatus(200);
            }
        } else if (data.get("move").getAsString().equals("update")) {
            try {
                updateAction(req,resp);
            } catch (Exception e) {
                System.err.println("Error reached while running POST.");
                Map<String, String> options = new LinkedHashMap<>();
                options.put("response", "Problem With Update");
                resp.setStatus(200);
            }
        }
    }

    /**
     * Create Action documentation
     */
    private void createAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // TODO Follow Jean's example regarding JSON and Gson.
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String json = null;

        // Gather parameters for new Customer
        String customerID = data.get("id").getAsString();
        String firstname = data.get("fn").getAsString();
        String lastname = data.get("ln").getAsString();
        String email = data.get("em").getAsString();
        Customer temp = new Customer(Integer.parseInt(customerID),firstname,lastname,email); // New Customer
        customerDAO.save(temp);     // Add to DB
        Customer result = customerDAO.findById(temp.getEmail());    // New Customer info

        // Display new Customer info
        Map<String, String> options = new LinkedHashMap<>();
        options.put("id",(String.valueOf(result.getCustomerID())));
        options.put("firstname",(String.valueOf(result.getFirstname())));
        options.put("lastname",(String.valueOf(result.getLastname())));
        options.put("email",(String.valueOf(result.getEmail())));
        json = new Gson().toJson(options);
        System.out.println(json);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
        //resp.setStatus(201);
    }

    /**
     * Read (Search) Action documentation
     */
    private void readAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // TODO Follow Jean's example regarding JSON and Gson.
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String json = null;

        // Get Email to search for
        String email = data.get("em").getAsString();
        Customer result = customerDAO.findById(email); // Return customer is found, null (exception) if not found

        // Display Customer
        Map<String, String> options = new LinkedHashMap<>();
        options.put("id",(String.valueOf(result.getCustomerID())));
        options.put("firstname",(String.valueOf(result.getFirstname())));
        options.put("lastname",(String.valueOf(result.getLastname())));
        options.put("email",(String.valueOf(result.getEmail())));
        json = new Gson().toJson(options);
        System.out.println(json);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
        //resp.setStatus(201);
    }

    /**
     * Read All (Find All) Action documentation
     */
    private void readAllAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // TODO Follow Jean's example regarding JSON and Gson.
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String json = null;

        // Display Customer ArrayList
        ArrayList<Customer> customers = customerDAO.findAll();      // All Customers gotten as ArrayList
        Map<String, ArrayList> options = new LinkedHashMap<>();
        options.put("customers", customers);
        json = new Gson().toJson(options);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
        //resp.setStatus(201);
    }

    /**
     * Update Action documentation
     */
    private void updateAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // TODO Follow Jean's example regarding JSON and Gson.
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String json = null;

        // Obtain Parameters
        String customerID = data.get("id").getAsString();   // New customerID
        String firstname = data.get("fn").getAsString();    // New firstname
        String lastname = data.get("ln").getAsString();     // New lastname
        String email = data.get("em").getAsString();        // Email of the Customer we want to update
        Customer target = customerDAO.findById(email);

        // Customer with new information to replace existing entry
        Customer temp = new Customer(Integer.parseInt(customerID),firstname,lastname,email);
        customerDAO.update(temp,target.getEmail());     // Update target Customer
        Customer result = customerDAO.findById(temp.getEmail());    // Updated Customer info

        // Display new Customer info
        Map<String, String> options = new LinkedHashMap<>();
        options.put("id",(String.valueOf(result.getCustomerID())));
        options.put("firstname",(String.valueOf(result.getFirstname())));
        options.put("lastname",(String.valueOf(result.getLastname())));
        options.put("email",(String.valueOf(result.getEmail())));
        json = new Gson().toJson(options);
        System.out.println(json);
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
        //resp.setStatus(201)
    }
}

