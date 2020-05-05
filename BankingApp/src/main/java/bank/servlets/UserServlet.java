package bank.servlets;

import bank.dataaccess.UserDAO;
import bank.model.User;
import bank.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        //String email = req.getParameter("email");
        //String password = req.getParameter("password");
        //User newUser = new User(req.getParameter("email"), req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("password"), req.getParameter("phoneNumber"), req.getParameter("role"));
        Cookie[] cookies = req.getCookies();
        User user;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("userEmail")) {
                if(us.retrieveUserByEmail(cookies[i].getValue()).getRole().equals("admin")) {
                    UserDAO userDAO = new UserDAO();
                    userDAO.save(new User("june@gmail.com", "June", "Smith", "12345", "1234567890", "customer"));
                }
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = new User(req.getParameter("email"), req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("password"), req.getParameter("phoneNumber"), req.getParameter("role"));
        us.createUser(newUser);

//        if (us.login(email, password)) resp.setStatus(201);
//        else resp.setStatus(206);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
