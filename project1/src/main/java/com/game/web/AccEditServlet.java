package com.game.web;

import com.game.service.accountservices.CreationService;
import com.game.service.accountservices.FriendService;
import com.game.service.accountservices.ModificationService;
import com.game.utils.ConnectionUtils;
import com.game.utils.PostgresConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class AccEditServlet extends HttpServlet {
    ModificationService modificationService;
    FriendService friendService;
//    ConnectionUtils connectionUtils = new PostgresConnectionUtil("jdbc:postgresql://rjdatabase-1.cbfjnm41xkat.us-east-1.rds.amazonaws.com:5432/Project_1",
//            "gameportal_user",
//            "ge4s1lly",
//            "public");
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        String email = req.getParameter("email");
        String friend = getInitParameter("friend");
//        List<String> friends = Collections.singletonList(req.getParameter("friends"));
        String bankAccount = req.getParameter("bankAccount");
//        String amount = req.getParameter("amount");
        int amount = Integer.parseInt(req.getParameter("amount"));


        modificationService.changePassword(password, username);
        modificationService.changeBankAccount(bankAccount, username);
        modificationService.deposit(amount, username);
        modificationService.withdraw(amount, username);

//        friendService.getFriends(username);
        friendService.addFriend(username, friend);
        friendService.removeFriend(username, friend);

        resp.getWriter().write("<html><body><b>Your account info has been updated!.</b></body></html>");
        out.close();
    }
}
