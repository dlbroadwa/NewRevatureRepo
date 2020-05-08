package dev.web;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Project 1:<br>
 * <br>
 *  The LogoutServlet class serves as a Java Servlet that will allow users to log out of the system by setting the
 *    user session cookie to null.
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
public class LogoutServlet extends HttpServlet {

    // Instance Variables

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
        System.out.println("Servicing LogoutServlet");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        /*
         * destroy -- gets called when the server needs it to be.
         *           most likely at server shutdown.
         * */
        System.out.println("Destroy LogoutServlet");
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
        System.out.println("Init LogoutServlet");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Take the user cookie
        Cookie loginCookie = null;
        Cookie[] cookies = req.getCookies();
        if ( cookies != null ) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("user")) {
                    loginCookie = cookie;
                    break;
                }
            }
        }
        if ( loginCookie != null ) {
            loginCookie.setMaxAge(0); //Setting the cookie's maximum age to 0 deletes it from the client browser immediately.
            resp.addCookie(loginCookie);
        }
        resp.sendRedirect("/shelterwebapp");
    }
}