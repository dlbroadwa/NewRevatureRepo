package com.game.web;

import com.game.service.accountservices.ModificationService;
import com.game.service.messageservices.MessageService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * handles messaging related requests
 */
public class MessageServlet extends HttpServlet {
    MessageService messageService;

    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        messageService = (MessageService) context.getAttribute("messageService");
    }

    /**
     * Sends a message saving it to the message repository
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fromUser = req.getParameter("username");
        String toUser = req.getParameter("username");
        String content = req.getParameter("content");
        String username = req.getParameter("username");

        resp.getWriter().write("Message sent.");
        messageService.send(toUser, content, fromUser);
        resp.getWriter().write("Messages cleared!");
        messageService.clear(username);
    }

    /**
     * Gets the list of messages to display
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        messageService.getMessageList(username);
    }

}
