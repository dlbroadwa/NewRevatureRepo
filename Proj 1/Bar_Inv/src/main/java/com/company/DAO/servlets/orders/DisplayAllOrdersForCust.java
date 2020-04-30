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
public class DisplayAllOrdersForCust extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        List<Order> tmp = null;
        tmp = OrdersDAO.findAllForName(name);

        //create a table and fill it
        //create table and first row with the header
        out.print("<h2>View Inventory</h2>");
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Order ID</th><th>Customer Name</th><th>Item ID</th>"+
                "<th>Quantity</th><th>Mark Complete</th></tr>");
        for (Order o:tmp){
            out.print("<tr><td>"+o.getOrderID()+"</td><td>"+o.getCustomerName()+"</td><td>"+o.getItemID()+"</td>"+
                    "<td>"+o.getQuantity()+"</td><td>"+o.getMarked_complete()+"</td><td><a href='MarkCompleted?id="+o.getOrderID()+"</tr>");
        }
        out.print("</table>");



        out.close();
    }
}
