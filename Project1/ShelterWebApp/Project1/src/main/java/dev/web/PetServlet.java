package dev.web;

import dev.app.ShelterApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.models.pet.Cat;
import dev.models.pet.Dog;
import dev.models.pet.Pet;
import dev.models.pet.PetList;
import dev.services.PetService;

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
         *         You can preload servlet with <load-on-startup> in the dev.web.xml.
         * */
        System.out.println("Init PetServlet");
        ShelterApplication app = ShelterApplication.getInstance();
        petService = app.getPetServ();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the Request URL and look for a specific extension to dictate which action should be performed based
        // on the submitted form.
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
        } else if ( URL.contains("/searchQuery") ) {
            action = "/searchQuery";
        }

        // Run doPost on certain paths given information context.
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
                case "/searchQuery":
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
        // Get the Request URL and look for a specific extension to dictate which action should be performed based
        // on the submitted form.
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
        } else if ( URL.contains("/searchQuery") ) {
            action = "/searchQuery";
        }

        // Run doGet on certain paths given information context.
        try {
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
                case "/searchQuery":
                    searchAction(req, resp);
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

    /**
     * Takes the parameters submitted from the Create form to create a new Pet instance to add it to the
     *   Postgresql database through the PetService.
     * Displays a .jsp page with the outcome of the action. Pressing the Back button the browser returns the user
     *   to the Pet Forms.
     */
    private void createAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String pettype = req.getParameter("pettypeC");
        String name = req.getParameter("nameC");
        String petid = req.getParameter("petidC");
        String breed = req.getParameter("breedC");
        String petgender = req.getParameter("petgenderC");
        String petage = req.getParameter("petageC");

        Pet newPet = null;
        // Check the Pet subclass to determine what Pet should be made.
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

    /**
     * Takes the parameters submitted from the Read form to display a Pet instance specifed by the ID number given from
     *   the Postgresql database through the PetService.
     * Displays a .jsp page with the outcome of the action. Pressing the Back button the browser returns the user
     *   to the Pet Forms.
     */
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

    /**
     * Takes the parameters submitted from the Update form to update a Pet instance specified by the ID number given
     *   from the Postgresql database through the PetService.
     * Displays a .jsp page with the outcome of the action. Pressing the Back button the browser returns the user
     *   to the Pet Forms.
     */
    private void updateAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String name = req.getParameter("nameU");
        String petid = req.getParameter("petidU");
        String breed = req.getParameter("breedU");
        String petage = req.getParameter("petageU");

        Pet target = petService.searchByID(Integer.parseInt(petid));
        Pet upToDatePet = null;
        Pet result = null;

        // Check the Pet subclass to determine what replacement Pet should be made.
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

    /**
     * Takes the parameters submitted from the Delete form to remove a Pet instance specified by the ID number given
     *   from the Postgresql database through the PetService.
     * Displays a .jsp page with the outcome of the action. Pressing the Back button the browser returns the user
     *   to the Pet Forms.
     */
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

    /**
     * Retrieves a PetList instance created by the PetService that serves as a wrapper class instance that will allow
     *   its internal array of Pets to be converted to JSON for use by the Search Page.
     * Contents will be read and filtered as needed on the Search Page.
     */
    private void searchAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        PetList allPets = petService.getAllPetsAsList();
        ObjectMapper mapper = new ObjectMapper();
        String PetListJSON = mapper.writeValueAsString(allPets);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(PetListJSON);
    }
}
