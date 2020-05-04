package com.game.web;

import com.game.service.accountservices.ModificationService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AccEditServlet extends HttpServlet {
    ModificationService modificationService;

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter out = resp.getWriter();
//
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
////        String email = req.getParameter("email");
//        String friend = getInitParameter("friend");
////        List<String> friends = Collections.singletonList(req.getParameter("friends"));
//        String bankAccount = req.getParameter("bankAccount");
////        String amount = req.getParameter("amount");
//        int amount = Integer.parseInt(req.getParameter("amount"));
//
//
//        modificationService.changePassword(password, username);
//        modificationService.changeBankAccount(bankAccount, username);
//        modificationService.deposit(amount, username);
//        modificationService.withdraw(amount, username);
//
////        friendService.getFriends(username);
////        friendService.addFriend(username, friend);
////        friendService.removeFriend(username, friend);
//
//        resp.getWriter().write("<html><body><b>Your account info has been updated!.</b></body></html>");
//        out.close();
//    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        modificationService = (ModificationService) context.getAttribute("modificationService");
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String bankAccount = req.getParameter("bankAccount");

        modificationService.changePassword(password, username);
        modificationService.changeBankAccount(bankAccount, username);
    }
}
