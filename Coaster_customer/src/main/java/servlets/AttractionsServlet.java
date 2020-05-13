package main.java.servlets;

import com.google.gson.Gson;
import dao.AttractionDAO;
import models.Attraction;
import utils.PostgresConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AttractionsServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        {
            AttractionDAO repo = new AttractionDAO(new PostgresConnectionUtil());
            if (req.getHeader("find").equals("all"))
            {
                String json = null;
                List<Attraction> all = repo.findAll();
                Map<String, List> options = new LinkedHashMap<>();
                options.put("attraction", all);
                //System.out.println(options);
                json = new Gson().toJson(options);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
            }
            else if (!(req.getHeader("find") == null)) {
                try {
                    String json = null;
                    Attraction temp = repo.findById(new Integer(req.getHeader("find")));
                    Map<String, Attraction> options = new LinkedHashMap<>();
                    options.put("attraction", temp);
                    //System.out.println(options);
                    json = new Gson().toJson(options);
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write(json);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
