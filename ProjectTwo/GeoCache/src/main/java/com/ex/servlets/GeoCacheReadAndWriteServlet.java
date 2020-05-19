package com.ex.servlets;

import com.ex.model.DifficultyLevel;
import com.ex.model.GeoCashe;
import com.ex.model.Item;
import com.ex.services.GpsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/***
 * This servlet class processes the requests to read and write geocaches.
 *
 * @author Shawyn Kane
 */
//@WebServlet("/GeoCacheReadAndWriteServlet")
public class GeoCacheReadAndWriteServlet extends HttpServlet {
    private GpsService gpsService;

    @Override
    public void init() throws ServletException {
        super.init();
        gpsService = new GpsService();
    }

    /***
     * This method processes the request to read all geocaches currently in persistent storage.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(gpsService.getAllCashes()));
    }

    /***
     * This method processes the request to write/create a new geocache in persistent storage.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GeoCashe geoCashe = new GeoCashe(null, req.getParameter("imageurl"),
                req.getParameter("gpslocation"), new DifficultyLevel(Integer.parseInt(req.getParameter("difficultylevelid"))));
        if(gpsService.createNewGeoCashe(geoCashe)) resp.setStatus(201);
        else resp.setStatus(206);
    }
}
