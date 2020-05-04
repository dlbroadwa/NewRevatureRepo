package com.game.web;

import com.game.data.AccountSQLRepo;
import com.game.models.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.game.service.accountservices.CreationService;

public class CreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Below creates new account and adds to account table in database. Will only fill 3 columns.
        // In account updating will allow addition of addtional info into remaining columns if needed.
        //AccountSQLRepo aRepo = new AccountSQLRepo(connectionUtils);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        resp.getWriter().write("<html><body><b>New Account Created! Please login to your account.</b></body></html>");
        CreationService creationService = (CreationService) getServletContext().getAttribute("creationService");
        creationService.signUp(username, password, email);
    }
}
