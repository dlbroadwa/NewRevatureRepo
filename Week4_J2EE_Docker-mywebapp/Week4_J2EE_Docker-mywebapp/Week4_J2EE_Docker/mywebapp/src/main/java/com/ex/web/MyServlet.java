package com.ex.web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MyServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
    System.out.println("Servicing MyServlet");
    super.service(req, resp);
  }

  @Override
  public void destroy() {
    /*
    * destroy -- gets called when the server needs it to be.
    *           most likely at server shutdown.
    * */
    System.out.println("Destroy MyServlet");
    super.destroy();
  }

  @Override
  public void init() throws ServletException {
    /*
    * init -- beginning of the servlet lifecycle
    *         it runs once, if the servlet has never been initialize
    *         when the first request to a matching url pattern is made.
    *         You can preload servlet with <load-on-startup> in the web.xml.
    * */
    System.out.println("Init MyServlet");
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {

    JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
    System.out.println("step one");
    String foo = data.get("foo").getAsString();
    String bar = data.get("bar").getAsString();
    String baz = data.get("baz").getAsString();
    System.out.println(String.format("%s, %s, %s.",foo,bar,baz));
    System.out.println("Left Servlet");
    resp.setContentType("application.json");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().write(String.valueOf(data));

//    if(name != null) {
//
//      resp.getWriter().write("Hello, " + name);
//      resp.setStatus(201);
//      resp.setContentType("text/plain");
//    } else {
//      resp.getWriter().write("Hello, World!");
//      resp.setStatus(201);
//      resp.setContentType("text/plain");
//    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //String name = req.getParameter("myname");


     JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
    System.out.println("step one");
    String foo = data.get("foo").getAsString();
    String bar = data.get("bar").getAsString();
    String baz = data.get("baz").getAsString();
    System.out.println(String.format("%s, %s, %s.",foo,bar,baz));
    System.out.println("Left Servlet");
    resp.setContentType("application.json");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().write(String.valueOf(data));
//
//    if(name != null) {
//      resp.getWriter().write("Hello, " + name);
//      resp.setStatus(201);
//      resp.setContentType("text/plain");
//    } else {
//      resp.getWriter().write("Hello, World!");
//      resp.setStatus(201);
//      resp.setContentType("text/plain");
//    }
  }
}
