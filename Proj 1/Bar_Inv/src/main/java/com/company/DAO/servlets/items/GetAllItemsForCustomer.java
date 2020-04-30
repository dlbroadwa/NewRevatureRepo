package com.company.DAO.servlets.items;

import com.company.DAO.data.web.ItemsDAO;
import com.company.DAO.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetAllItemsForCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        List<Item> tmp = null; //hmmm... gotta figure this out
        tmp = ItemsDAO.findAll();

        //create a table and fill it
        //create table and first row with the header
        out.print("<h2>View Inventory</h2>");
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Item Name</th><th>Item ID</th><th>Number in Stock</th><th>Add to Order</th></tr>");
        //fill the table
        for (Item i:tmp){
            out.print("<tr><td>"+i.getItemName()+"</td><td>"+i.getId()+"</td><td>"+i.getOnHand()+"</td>"+
                    //links to add item to order
                    "<td><a href='AddOrder?id="+i.getId()+"'>Order</a></td>"+
                    "</tr>");
        }
        out.print("</table>");

        out.close();
    }
}
