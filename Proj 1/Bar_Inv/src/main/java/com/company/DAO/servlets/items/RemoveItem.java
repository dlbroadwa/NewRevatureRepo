package com.company.DAO.servlets.items;

import com.company.DAO.data.ItemsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
//        ItemsDAO.delete(id);
        //what do we want to show when we delete something?
        resp.sendRedirect("GetAllItemsAdmin");
    }
}
