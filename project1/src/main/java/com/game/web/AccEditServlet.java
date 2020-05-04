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
