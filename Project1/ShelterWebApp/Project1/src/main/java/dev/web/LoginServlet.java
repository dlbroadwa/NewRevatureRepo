package dev.web;

import dev.app.ShelterApplication;
import dev.models.user.User;
import dev.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Project 1:<br>
 * <br>
 *  The LoginServlet class serves as a Java Servlet that holds an extension of UserService while running the application
 *    within a Tomcat container or within a Docker image.
 *  This will allow the ability for users to authenticate themselves into the application as long as the Tomcat/Docker
 *    service is enabled, which consists of a process where the given credential inputs are
 *    searched for and compared with the data present in the user database.
 *
 *  <br> <br>
 *  Created: <br>
 *     04 May 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     04 May 2020, Barthelemy Martinon,    Created class.
 *     										  Prototyped init, service, destroy, doGet, and doPost.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 04 May 2020
 */
public class LoginServlet extends HttpServlet {

    // Instance Variables
    private UserService userService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
        System.out.println("Servicing LoginServlet");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        /*
         * destroy -- gets called when the server needs it to be.
         *           most likely at server shutdown.
         * */
        System.out.println("Destroy LoginServlet");
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
        System.out.println("Init LoginServlet");
        ShelterApplication app = ShelterApplication.getInstance();
        userService = app.getUserServ();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = req.getParameter("userid");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User target = userService.searchByID(Integer.parseInt(userid));
        User result = null;
        Boolean authStatus = false;
        if (target != null) {
            if (target.userAuth(username, password) == true) {
                authStatus = true;
                result = target;
            }
        }

        if (result != null && authStatus) {
            Cookie loginCookie = new Cookie("user", userid);
            // Set Cookie to expire in 15 minutes
            loginCookie.setMaxAge(15 * 60);
            resp.addCookie(loginCookie);

            // Redirect user to a certain page depending on the User's type
            if (result.getUserType().equals("customer")) {
                resp.sendRedirect("adoptionPage.html");
            } else if (result.getUserType().equals("employee")) {
                resp.sendRedirect("petForm.html");
            } else if (result.getUserType().equals("admin")) {
                resp.sendRedirect("userForm.html");
            }
        } else if (!authStatus) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/userAuth.html");
            resp.getWriter().println("Incorrect Username and/or Password");
            rd.include(req,resp);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/userAuth.html");
            resp.getWriter().println("No User was found with matching ID or there is a problem.");
            rd.include(req,resp);
        }
    }
}