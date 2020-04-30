package com.ex.servlet;

import com.ex.model.User;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Portal extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //get session variables
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loggedUser");
        System.out.printf("USER FROM SESSION: %s", user);

        //If no one is logged in, user=null... we need to return out to safeguard nullpointer & simply go back to index.html
        if(user == null) {
            resp.sendRedirect("index.html");
            return;
        }

        //switch on session::loggedUser - redirect to appropriate page
        switch(user.getUseraccess()){
            case "admin":
//                req.setAttribute("message", "THIS USER");
//                RequestDispatcher disp = getServletContext().getRequestDispatcher("/adminportal.html");
//                disp.forward(req, resp);
                resp.sendRedirect("adminportal.html");
                break;
            case "coach":
                resp.sendRedirect("adminportal.html");
                break;
            case "player":
                resp.sendRedirect("adminportal.html");
                break;
            default:
                resp.sendRedirect("adminportal.html");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loggedUser");
        System.out.printf("USER FROM SESSION: %s", user);

        /* switch on session::loggedUser - Allows to propogate a response header to send to the page
            allowing for custom data to be given to each respective portal page.
         */
        switch(user.getUseraccess()) {
            case "admin":
                break;
            case "coach":
                break;
            case "player":
                break;
            default:
                break;
        }

        //Create a JSON object for javascript to pickup and parse
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String jsonData = new Gson().toJson(user);
        out.print(jsonData);
        out.flush();
    }
}
