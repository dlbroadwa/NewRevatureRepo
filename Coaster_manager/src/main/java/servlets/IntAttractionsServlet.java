package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.SQLDatabaseIntAttraction;
import models.Attraction;
import utils.PostgresConnectionUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  Project 2:<br>
 * <br>
 *  IntAttractionServlet
 *
 *  <br> <br>
 *  Created: <br>
 *     May 13, 2020 Paityn Maynard<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *
 * <br>
 *  @author
 *  @version 11 May 2020
 *
 */

public class IntAttractionsServlet extends HttpServlet {
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
        if(!(data.get("add") ==null)) {
            String json = null;

            try {
                String name = data.get("name").getAsString();
                int id = data.get("id").getAsInt();
                int rating = data.get("rate").getAsInt();
                String imUrl = data.get("url").getAsString();
                String status = "Operational";

                Attraction attraction = new Attraction(name, status, imUrl, id, rating);
                SQLDatabaseIntAttraction intAttract = new SQLDatabaseIntAttraction(new PostgresConnectionUtil());
                    intAttract.add(attraction);

                Map<String,String> options = new LinkedHashMap<>();
                options.put("name",(String.valueOf(attraction.getName())));
                options.put("id",(String.valueOf(attraction.getId())));
                options.put("rate",(String.valueOf(attraction.getRating())));
                options.put("url",(String.valueOf(attraction.getImageurl())));
                options.put("status",(String.valueOf(attraction.getStatus())));

               json = new Gson().toJson(options);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {

        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
