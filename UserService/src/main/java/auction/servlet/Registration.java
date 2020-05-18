package auction.servlet;
import auction.dataaccess.UserDAO;
import auction.models.User;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();

        response.setContentType("text/html");

        String userName=request.getParameter("userName");
        String password=request.getParameter("userPass");
        User newUser = new User(userName, password);
        userDAO.save(newUser);
    }
}
