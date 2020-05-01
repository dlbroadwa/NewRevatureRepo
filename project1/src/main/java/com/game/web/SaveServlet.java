package com.game.web;

import com.game.data.AccountSQLRepo;
import com.game.models.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.game.service.accountservices.AccountDetailService;
import com.game.service.accountservices.CreationService;
import com.game.service.accountservices.CreationServiceImp;
import com.game.system.AuthenticationStatus;
import com.game.service.accountservices.AccountDetailServiceImp;
import com.game.utils.ConnectionUtils;
import com.game.utils.PostgresConnectionUtil;

public class SaveServlet extends HttpServlet {
//    AccountDetailService newAccount;
    CreationService creationService;
    ConnectionUtils connectionUtils = new PostgresConnectionUtil("jdbc:postgresql://rjdatabase-1.cbfjnm41xkat.us-east-1.rds.amazonaws.com:5432/Project_1",
            "gameportal_user",
            "ge4s1lly",
            "public");

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
        /*aRepo = new AccountSQLRepo(connectionUtils);
        newAccount = new AccountDetailServiceImp(aRepo);
        creationService = new CreationServiceImp(newAccount);

        System.out.println("Servicing MyServlet");
        super.service(req, resp);*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Below creates new account and adds to account table in database. Will only fill 3 columns.
        // In account updating will allow addition of addtional info into remaining columns if needed.
        AccountSQLRepo aRepo = new AccountSQLRepo(connectionUtils);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        creationService.signUp(username, password, email);
        resp.getWriter().write("New Account Created! Please login to your account.");
    }
}
