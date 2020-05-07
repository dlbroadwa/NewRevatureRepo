package bank.servlets;

import bank.model.User;
import bank.services.UserServices;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mortbay.util.ajax.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class UserProfileServlet extends HttpServlet {
    UserServices us;
    @Override
    public void init() throws ServletException {
        super.init();
        us = new UserServices();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject job = new JSONObject();
        JSONArray jary = new JSONArray();
        ArrayList<User> users = new ArrayList<User>();
        String email = req.getSession().getAttribute("userEmail").toString();
        User user = us.retrieveUserByEmail(email);
        job.put("firstname", user.getEmail());
        job.put("lastname", user.getLastName());
        job.put("email", user.getEmail());
        job.put("phone", user.getPhoneNumber());
        jary.put(job);
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
