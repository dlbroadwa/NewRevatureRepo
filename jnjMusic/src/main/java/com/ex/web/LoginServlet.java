package com.ex.web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.UserRepo;
import models.Users;
import utils.ConnectionUtils;
import utils.PostgresConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    //Called for nothing/ no purposes
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);
    }
    //Called for registering a new user or called for logging in
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String json = null;
        if (data.get("move").getAsString().equals("new"))
        {
            try
            {
                //System.out.println("new user");
                String email = data.get("em").getAsString();
                String password = data.get("pw").getAsString();
                String phone = data.get("ph").getAsString();
                String primaryIn = data.get("pi").getAsString();
                UserRepo repo = new UserRepo(new PostgresConnectionUtil());
                Users temp = new Users(email,password,phone,primaryIn, false);
                repo.save(temp);
                Users user = repo.findById(temp.getEmail());
                Map<String, String> options = new LinkedHashMap<>();
                options.put("email", email);
                json = new Gson().toJson(options);
                System.out.println(json);
                //resp.setStatus(201);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Map<String, String> options = new LinkedHashMap<>();
                options.put("response", "Email Already Exists");
                json = new Gson().toJson(options);
                //resp.setStatus(200);
            }
        }
        else if (data.get("move").getAsString().equals("login"))
        {
            try
            {
                Users test = new UserRepo(new PostgresConnectionUtil()).findById(data.get("em").getAsString());
                //System.out.println("Made a Base User");
                if (test == null)
                {
                    //System.out.println("Bad email");
                    Map<String, String> options = new LinkedHashMap<>();
                    options.put("response", "Please register for an Account!!");
                    json = new Gson().toJson(options);
                }
                else if (test.getPassword().equals(data.get("pw").getAsString()))
                {

                        Map<String, String> options = new LinkedHashMap<>();
                        options.put("email", test.getEmail());
                        options.put("privy", test.getAdmin().toString());
                        json = new Gson().toJson(options);

                        //resp.setStatus(200);
                }
                else
                {
//                    System.out.println(test.getPassword());
//                    System.out.println(data.get("pw").getAsString());
                    Map<String, String> options = new LinkedHashMap<>();
                    options.put("response", "Bad Password");
                    json = new Gson().toJson(options);
                }


            }
            catch (Exception e)
            {
                e.printStackTrace();
                Map<String, String> options = new LinkedHashMap<>();
                options.put("response", "email was invalid!");
                json = new Gson().toJson(options);
                //resp.setStatus(400);
            }
        }
        else
        {
            //resp.setStatus(400);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
    //Called to Update customer Phone number or name
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    //Not used, we will not remove customer data or inventory manually
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    //shouldn't be overriding this.
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//    }
}
