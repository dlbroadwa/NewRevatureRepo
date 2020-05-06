package com.ex.servlet;

import com.ex.model.Person;
import com.ex.model.User;
import com.ex.service.CoachService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RenameTeam extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loggedUser");
        System.out.printf("USER FROM SESSION: %s \n", user);

        //If no one is logged in, user=null... we need to return out to safeguard nullpointer & simply go back to index.html
        if(user == null || !user.getUseraccess().equals("coach") ) {
            resp.sendRedirect("index.html");
            return;
        }

        //Check to see if coach is on a team - if not - alert there are no teams & close window
        CoachService service = new CoachService();

        //Build up a coach object by checking agains tlogged in user from session converted to coach
        Person coach = null;
        try {
            coach = service.getCoach(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(coach.getTeam().getName() == null) {
            JsonObject obj = new JsonObject();
            obj.addProperty("isOnTeam", 0);
            //Create a JSON object for javascript to pickup and parse
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            PrintWriter out = resp.getWriter();
            out.print(obj);
            out.flush();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loggedUser");
        String newName = req.getParameter("newName");
        CoachService service = new CoachService();

        //Build up a coach object by checking agains tlogged in user from session converted to coach
        Person coach = null;
        try {
            coach = service.getCoach(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Finally transact a rename function
        try {
            service.renameTeam(coach.getTeam().getName(), newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
