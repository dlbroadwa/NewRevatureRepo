package com.game.web;

import com.game.service.accountservices.AccountDetailService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        AccountDetailService accountDetailService = (AccountDetailService) getServletContext().getAttribute("accountDetailService");
        accountDetailService.removeAccount(username);
    }
}
