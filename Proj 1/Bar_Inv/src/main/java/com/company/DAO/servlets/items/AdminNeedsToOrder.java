package com.company.DAO.servlets.items;

import com.company.DAO.data.ItemsDAO;
import com.company.DAO.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class AdminNeedsToOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            List<Item> tmp1 = null;
            List<Item> tmp2 = null;
            List<Item> tmp3 = null;
            String createTable= "<table border='1' width='100%'>";
            String tableHeader= "<tr><th>Item Name</th><th>Item ID</th><th>Number in Stock</th>"+
                    "<th>Low Level</th><th>Optimal Level</th><th>Edit</th><th>Remove</th></tr>";

        tmp1 = ItemsDAO.compareColumns("onhand","optlevel","<=");
        tmp2 = ItemsDAO.compareColumns("onhand","lowlevel","<=");
        tmp3 = ItemsDAO.compareColumns("onhand","0","<=");

        //create a table and fill it
            //create table and first row with the header
            out.print("<h2>View Low Inventory</h2>");
            out.print("<h4>Order these soon</h4>");
            out.print("<span>These are the items where the number in stock are less than the optimal stock level</span>>");
            out.print(createTable);
            out.print(tableHeader);
            //fill the table
            for (Item i:tmp1){
                out.print("<tr><td>"+i.getItemName()+"</td><td>"+i.getId()+"</td><td>"+i.getOnHand()+"</td>"+
                        "<td>"+i.getLowLevel()+"</td><td>"+i.getOptLevel()+"</td>"+
                        //links to edit and remove items
                        "<td><a href='DisplayUpdateStuff?id"+i.getId()+"'>Edit</a></td><td><a href='RemoveItem?id="+i.getId()+"' >Remove</a></td>"+
                        "</tr>");
            }
            out.print("</table>");

        out.print("<h4>Order these now</h4>");
        out.print("<span>These are the items where the number in stock are less than the low stock level</span>>");
        out.print(createTable);
        out.print(tableHeader);
        //fill the table
        for (Item i:tmp2){
            out.print("<tr><td>"+i.getItemName()+"</td><td>"+i.getId()+"</td><td>"+i.getOnHand()+"</td>"+
                    "<td>"+i.getLowLevel()+"</td><td>"+i.getOptLevel()+"</td>"+
                    //links to edit and remove items
                    "<td><a href='DisplayUpdateStuff?id"+i.getId()+"'>Edit</a></td><td><a href='RemoveItem?id="+i.getId()+"' >Remove</a></td>"+
                    "</tr>");
        }
        out.print("</table>");

        out.print("<h4>Order these yesterday</h4>");
        out.print("<span>These are the items that are out of stock or on back order</span>>");
        out.print(createTable);
        out.print(tableHeader);
        //fill the table
        for (Item i:tmp3){
            out.print("<tr><td>"+i.getItemName()+"</td><td>"+i.getId()+"</td><td>"+i.getOnHand()+"</td>"+
                    "<td>"+i.getLowLevel()+"</td><td>"+i.getOptLevel()+"</td>"+
                    //links to edit and remove items
                    "<td><a href='DisplayUpdateStuff?id"+i.getId()+"'>Edit</a></td><td><a href='RemoveItem?id="+i.getId()+"' >Remove</a></td>"+
                    "</tr>");
        }
        out.print("</table>");
        out.close();

    }
}
