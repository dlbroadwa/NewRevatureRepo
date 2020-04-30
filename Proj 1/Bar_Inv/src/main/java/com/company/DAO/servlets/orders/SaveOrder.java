package com.company.DAO.servlets.orders;

import com.company.DAO.data.web.OrdersDAO;
import com.company.DAO.models.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Order o = new Order();

//        o.setCustomerName();
        o.setItemID(Integer.parseInt(req.getParameter("id")));
        o.setQuantity(Integer.parseInt(req.getParameter("quant")));

        OrdersDAO.save(o);
        resp.sendRedirect("GetAllItemsForCustomer");
    }
}
