package com.company.DAO.servlets.orders;

import com.company.DAO.data.web.ItemsDAO;
import com.company.DAO.data.web.OrdersDAO;
import com.company.DAO.models.Item;
import com.company.DAO.models.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddOrder extends HttpServlet {
    OrdersDAO ordersDAO = OrdersDAO.getInstance();
    ItemsDAO itemsDAO = ItemsDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Item item = new Item();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        item = itemsDAO.findById(id);

        out.print("<h2>Order an Item</h2>");
        out.print("<form action='SaveOrder' method='get'>");
        out.print("<span id='item_name'>"+item.getItemName()+"</span>");
        out.print("<span>How many do you want?</span><input id='quant' type='number'/>");
        out.print("<input value='Save Order' type='submit'/>");
        out.print("</form>");

    }
}
