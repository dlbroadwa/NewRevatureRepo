package web;

import app.ShelterApplication;
import models.pet.Cat;
import models.pet.Dog;
import models.pet.Pet;
import services.PetService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 *  Project 1:<br>
 * <br>
 *  The PetServlet class serves as a Java Servlet that holds an extension of PetService while running the application
 *    within a Tomcat container or within a Docker image.
 *  This will allow PetService operations to be performed as long as the Tomcat/Docker service is enabled, which
 *    includes CRUD operations on the pet table. Different actions are determined through the detection of certain
 *    keywords within URLs.
 *
 *  <br> <br>
 *  Created: <br>
 *     30 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     30 April 2020, Barthelemy Martinon,    Created class.
 *     										  Prototyped init, service, destroy, doGet, doPost, createAction, readAction,
 *     										    updateAction and deleteAction.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 30 April 2020
 */
public class PetServlet extends HttpServlet {

    // Instance Variables
    private PetService petService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
        System.out.println("Servicing PetServlet");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        /*
         * destroy -- gets called when the server needs it to be.
         *           most likely at server shutdown.
         * */
        System.out.println("Destroy PetServlet");
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
        System.out.println("Init PetServlet");
        ShelterApplication app = ShelterApplication.getInstance();
        petService = app.getPetServ();
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
                    createAction(req, resp);
                    break;
                case "/searchID":
                    readAction(req, resp);
                    break;
                case "/update":
                    updateAction(req, resp);
                    break;
                case "/delete":
                    deleteAction(req, resp);
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

        switch (action) {
            case "/add":
                doGet(req, resp);
                break;
            case "/searchID":
                doGet(req, resp);
                break;
            case "/update":
                doGet(req, resp);
                break;
            case "/delete":
                doGet(req, resp);
                break;
            default:
                System.err.println("Default reached while running POST, meaning bad path.");
                break;
        }

//        try {
//            switch (action) {
//                case "/add":
//                    doGet(req, resp);
//                    break;
//                case "/searchID":
//                    doGet(req, resp);
//                    break;
//                case "/update":
//                    doGet(req, resp);
//                    break;
//                case "/delete":
//                    doGet(req, resp);
//                    break;
//                default:
//                    System.err.println("Default reached while running POST, meaning bad path.");
//                    break;
//            }
//        } catch (SQLException ex) {
//            throw new ServletException(ex);
//        }
    }

    // Switch Statement Case Methods

    private void createAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String pettype = req.getParameter("pettypeC");
        String name = req.getParameter("nameC");
        String petid = req.getParameter("petidC");
        String breed = req.getParameter("breedC");
        String petgender = req.getParameter("petgenderC");
        String petage = req.getParameter("petageC");

        Pet newPet = null;
        if (pettype.equals("dog")) {
            newPet = new Dog(Integer.parseInt(petid),name,breed,petgender,Integer.parseInt(petage));
        } else if (pettype.equals("cat")) {
            newPet = new Cat(Integer.parseInt(petid),name,breed,petgender,Integer.parseInt(petage));
        }
        Pet result = petService.addNewPet(newPet);

        if(result != null) {
            resp.getWriter().write("Created : " + result.printInfo());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No Pet was added, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }

    private void readAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String petid = req.getParameter("petidR1");
        Pet result = petService.searchByID(Integer.parseInt(petid));

        if(result != null) {
            resp.getWriter().write("Search Result: " + result.printInfo());
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
        String name = req.getParameter("nameU");
        String petid = req.getParameter("petidU");
        String breed = req.getParameter("breedU");
        String petage = req.getParameter("petageU");

        Pet target = petService.searchByID(Integer.parseInt(petid));
        Pet upToDatePet = null;
        Pet result = null;
        if (target.getPetType() == "dog") {
            upToDatePet = new Dog(Integer.parseInt(petid),name,breed,target.getGender(),Integer.parseInt(petage));
            result = petService.updatePet(upToDatePet, target.getID());
        } else if (target.getPetType() == "cat") {
            upToDatePet = new Cat(Integer.parseInt(petid),name,breed,target.getGender(),Integer.parseInt(petage));
            result = petService.updatePet(upToDatePet, target.getID());
        } else if (target == null) {
            // Do nothing
        }

        if(result != null) {
            resp.getWriter().write("Update Result: " + result.printInfo());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No Pet was found with matching ID, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }

    private void deleteAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String petid = req.getParameter("petidD");
        Pet result = petService.removePet(Integer.parseInt(petid));

        if(result != null) {
            resp.getWriter().write("Deleted : " + result.printInfo());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No Pet with Matching ID found, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }
}
