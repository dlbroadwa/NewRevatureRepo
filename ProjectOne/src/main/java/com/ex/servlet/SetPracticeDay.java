package com.ex.servlet;

import com.ex.model.Person;
import com.ex.model.User;
import com.ex.service.CoachService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

public class SetPracticeDay extends HttpServlet {
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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loggedUser");
        LocalDate practiceDay = LocalDate.parse(req.getParameter("practiceDay"));
        CoachService service = new CoachService();

//        System.out.println("PRACTICE DAY: " + practiceDay);


        //Build up a coach object by checking agains tlogged in user from session converted to coach
        Person coach = null;
        try {
            coach = service.getCoach(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(coach != null) {
//            service.setPracticeDay(practiceDay, coach.getTeam().getName());
        }
    }
}
