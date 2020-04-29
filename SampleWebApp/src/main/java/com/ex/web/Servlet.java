package com.ex.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    public void init() throws ServletException {
        System.out.println("Init Servlet");
        super.init();
    }

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Init Servlet");
        super.service(req, resp);
    }

    public void destroy() {
        System.out.println("Init Servlet");
        super.destroy();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstItem = req.getParameter("firstItem");
        String secondItem = req.getParameter("secondItem");
        String thirdItem = req.getParameter("thirdItem");
        String fourthItem = req.getParameter("fourthItem");


        resp.getWriter();
        String htmlResp = "<html>";
        htmlResp += "First Item: " + firstItem;
        htmlResp += "Second Item: " + secondItem;
        htmlResp += "Third Item: " + thirdItem;
        htmlResp += "Fourth Item: " + fourthItem;

        resp.getWriter().write(htmlResp);
        resp.setContentType("text/html");
    }
}