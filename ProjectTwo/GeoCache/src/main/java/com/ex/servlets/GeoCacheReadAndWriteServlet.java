package com.ex.servlets;

import com.ex.model.DifficultyLevel;
import com.ex.model.GeoCashe;
import com.ex.services.GpsService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/GeoCacheReadAndWriteServlet")
public class GeoCacheReadAndWriteServlet extends HttpServlet {
    private GpsService gpsService;

    @Override
    public void init() throws ServletException {
        super.init();
        gpsService = new GpsService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(gpsService.getAllCashes()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GeoCashe geoCashe = new GeoCashe(null, req.getParameter("imageurl"), req.getParameter("gpslocation"), new DifficultyLevel(Integer.parseInt(req.getParameter("difficultylevelid"))));
        if(gpsService.createNewGeoCashe(geoCashe)) resp.setStatus(201);
        else resp.setStatus(206);
    }
}
