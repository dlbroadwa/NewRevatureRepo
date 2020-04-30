package com.company.DAO.servlets.orders;

import com.company.DAO.data.web.OrdersDAO;
import com.company.DAO.models.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/")
public class DisplayOpenOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        List<Order> tmp = null;
        tmp = OrdersDAO.findAll();

        //create a table and fill it
        //create table and first row with the header
        out.print("<h2>View Inventory</h2>");
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Order ID</th><th>Customer Name</th><th>Item ID</th>"+
                "<th>Quantity</th><th>Mark Complete</th></tr>");
        for (Order o:tmp) {
            int complete = o.getMarked_complete();
            if (complete == 1) {
                //if complete, do nothing
            } else {
                out.print("<tr><td>" + o.getOrderID() + "</td><td>" + o.getCustomerName() + "</td><td>" + o.getItemID() + "</td>" +
                        "<td>" + o.getQuantity() + "</td><td><a href='MarkCompleted?id=" + o.getOrderID() + "</tr>");
            }
        }
        out.print("</table>");

        out.close();




    }
}
