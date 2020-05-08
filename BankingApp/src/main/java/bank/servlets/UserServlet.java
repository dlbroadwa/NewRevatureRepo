package bank.servlets;

import bank.model.User;
import bank.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    UserServices us;

    @Override
    public void init() throws ServletException {
        super.init();
        us = new UserServices();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();
        System.out.println(session.getAttribute("userEmail"));
        //User user = us.retrieveUserByEmail(session.getAttribute("userEmail").toString());

//        User user;
//        for (int i = 0; i < cookies.length; i++) {
//            if (cookies[i].getName().equals("userEmail")) {
//                user = us.retrieveUserByEmail(cookies[i].getValue());
//                JSONObject jsonObject = new JSONObject();
//
//
//            }
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getSession().getAttribute("userEmail").toString();
        if (us.retrieveUserByEmail(email).getRole().equals("admin")) {
            if(req.getParameter("action").equals("create"))
            {
                User newUser = new User(req.getParameter("email"), req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("password"), req.getParameter("phoneNumber"), req.getParameter("role"));
                if (us.createUser(newUser)) resp.setStatus(201);
                else resp.setStatus(206);
            }
            else if(req.getParameter("action").equals("delete"))
            {
                User newUser = new User(req.getParameter("email"), "", "", "", "", "");
                if (us.deleteUser(newUser)) resp.setStatus(201);
                else resp.setStatus(206);
            }
        } else resp.setStatus(206);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getSession().getAttribute("userEmail").toString();
        if (us.retrieveUserByEmail(email).getRole().equals("admin")) {
            User newUser = new User(req.getParameter("email"), req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("password"), req.getParameter("phoneNumber"), req.getParameter("role"));
            if (us.updateUser(newUser)) resp.setStatus(201);
            else resp.setStatus(206);
        } else resp.setStatus(206);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getSession().getAttribute("userEmail").toString();
        if (us.retrieveUserByEmail(email).getRole().equals("admin")) {
            User newUser = new User(req.getParameter("email"), "", "", "", "", "");
            if (us.deleteUser(newUser)) resp.setStatus(201);
            else resp.setStatus(206);
        } else resp.setStatus(206);
    }
}