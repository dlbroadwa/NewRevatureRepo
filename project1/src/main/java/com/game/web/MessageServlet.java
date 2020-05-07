package com.game.web;

import com.game.models.Message;
import com.game.service.accountservices.ModificationService;
import com.game.service.messageservices.MessageService;
import com.google.gson.Gson;

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
     * @param req request -holds toUser and content parameter
     * @param resp response - confirms if message has been sent
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = (String) session.getAttribute("username");
        String toUser = req.getParameter("toUser");
        String content = req.getParameter("content");

        resp.getWriter().write("Message sent.");
        messageService.send(toUser, content, username);
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
        HttpSession session = req.getSession();

        String username = (String) session.getAttribute("username");

        List<Message> messageList = messageService.getMessageList(username);
        String messageJSON = "";
        Writer out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        for (Message m:messageList) {
            messageJSON = new Gson().toJson(m);
            out.write(messageJSON);
            out.flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = (String) session.getAttribute("username");

        resp.getWriter().write("Messages cleared!");
        messageService.clear(username);
    }
}
