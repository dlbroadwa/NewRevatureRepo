package com.game.web;

import com.game.service.accountservices.AccountDetailService;
import com.game.service.accountservices.CreationService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    AccountDetailService accountDetailService;
    CreationService creationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        creationService = (CreationService) context.getAttribute("creationService");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = (String) session.getAttribute("username");
        creationService.delete(username);
    }
}
