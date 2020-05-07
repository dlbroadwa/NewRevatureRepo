package bank.servlets;

import bank.model.User;
import bank.services.UserServices;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class UserProfileServlet extends HttpServlet {
    UserServices us;
    @Override
    public void init() throws ServletException {
        super.init();
        us = new UserServices();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray jary = new JSONArray();
        String email = req.getSession().getAttribute("userEmail").toString();
        User user = us.retrieveUserByEmail(email);
        user.setPassword("***");
        jary.put(user);
        resp.getWriter().write(jary.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
