package bank.servlets;


import bank.model.User;
import bank.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 *
 * @author Shawyn Kane
 */
public class LoginServlet extends HttpServlet {
    UserServices us;

    @Override
    public void init() throws ServletException {
        super.init();
        us = new UserServices();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println(req.getParameterNames());
        System.out.println(email);
        System.out.println(password);
        if (us.login(email, password)) resp.setStatus(201);
        else resp.setStatus(206);
    }
}
