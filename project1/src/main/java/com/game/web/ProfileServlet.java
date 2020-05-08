package com.game.web;

import com.game.models.Account;
import com.game.service.accountservices.AccountDetailService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ProfileServlet extends HttpServlet {
    AccountDetailService accountDetailService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        accountDetailService = (AccountDetailService) context.getAttribute("accountDetailService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("profile.html").include(req, resp);

        HttpSession session = req.getSession();
        String username = (String)session.getAttribute("username");

        Account acctInfo = accountDetailService.findByID(username);
        String msgJSON = "";
        Writer out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new GsonBuilder().create();
        msgJSON = gson.toJson(acctInfo);
        out.write(msgJSON);
        out.flush();
        out.close();
//                if (session!=null) {
//            String username = (String)session.getAttribute("username");
////            resp.getWriter().write("Yo, " + username + "!");
//            accountDetailService.findByID(username);
//            resp.getWriter().write(username);
//        } else {
//            resp.getWriter().write("You haven't logged in yet =/");
//            req.getRequestDispatcher("index.html").include(req, resp);
//        }
    }
}
