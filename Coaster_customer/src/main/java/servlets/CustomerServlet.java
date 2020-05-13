package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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
        // Get the Request URL and look for a specific extension to dictate which action should be performed based
        // on the submitted form.
        String URL = req.getRequestURL().toString();
        String action = null;

        if ( URL.contains("/add") ) {
            action = "/add";
        } else if ( URL.contains("/searchEmail") ) {
            action = "/searchEmail";
        } else if ( URL.contains("/update") ) {
            action = "/update";
        }

        // Run doPost on certain paths given information context.
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
        }

        // Run doGet on certain paths given information context.
        try {
            switch (action) {
                case "/add":
                    createAction(req, resp);
                    break;
                case "/searchEmail":
                    doGet(req, resp);
                    break;
                case "/update":
                    updateAction(req, resp);
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
     * Create Action documentation
     */
    private void createAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // TODO Follow Jean's example regarding JSON and Gson.

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    /**
     * Read (Search) Action documentation
     */
    private void readAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // TODO Follow Jean's example regarding JSON and Gson.

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    /**
     * Takes the parameters submitted from the Update form to update a Pet instance specified by the ID number given
     *   from the Postgresql database through the PetService.
     * Displays a .jsp page with the outcome of the action. Pressing the Back button the browser returns the user
     *   to the Pet Forms.
     */
    private void updateAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // TODO Follow Jean's example regarding JSON and Gson.

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}

