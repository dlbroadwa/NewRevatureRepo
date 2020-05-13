package servlets;

import models.Attraction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AttractionsServlet extends HttpServlet {
//Instance Variables
    Attraction attraction = new Attraction();
//Methods

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String json = null;
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");



        resp.getWriter().write(json);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
