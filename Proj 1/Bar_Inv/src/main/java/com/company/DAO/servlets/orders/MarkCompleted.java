package com.company.DAO.servlets.orders;


import com.company.DAO.data.web.OrdersDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MarkCompleted extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        int status = OrdersDAO.markComplete(id);

        if(status!=0){
            out.print("<span>Order marked as complete!</span>");
            resp.sendRedirect("DisplayAllOrders");
        }else {
            out.print("Something went wrong, could not mark order as complete");
        }

        out.close();

    }
}
