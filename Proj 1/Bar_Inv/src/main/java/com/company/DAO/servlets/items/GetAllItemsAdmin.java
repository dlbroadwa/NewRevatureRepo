package com.company.DAO.servlets.items;

import com.company.DAO.data.ItemRepository;
import com.company.DAO.data.ItemsDAO;
import com.company.DAO.data.Repository;
import com.company.DAO.models.Item;
import com.company.app.BarInventoryApplication;
import org.apache.logging.log4j.core.appender.rewrite.MapRewritePolicy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class GetAllItemsAdmin extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        List<Item> tmp = null;
        tmp = ItemsDAO.findAll();

        //create a table and fill it
        //create table and first row with the header
        out.print("<h2>View Inventory</h2>");
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Item Name</th><th>Item ID</th><th>Number in Stock</th>"+
                "<th>Low Level</th><th>Optimal Level</th><th>Edit</th><th>Remove</th></tr>");
        //fill the table
        for (Item i:tmp){
            out.print("<tr><td>"+i.getItemName()+"</td><td>"+i.getId()+"</td><td>"+i.getOnHand()+"</td>"+
                    "<td>"+i.getLowLevel()+"</td><td>"+i.getOptLevel()+"</td>"+
                    //links to edit and remove items
                    "<td><a href='DisplayUpdateStuff?id"+i.getId()+"'>Edit</a></td><td><a href='RemoveItem?id="+i.getId()+"' >Remove</a></td>"+
                    "</tr>");
        }
        out.print("</table>");
        //this links to the page that prints out the items that need to be ordered (onhand<low, etc)
        out.print("<a href='AdminNeedsToOrder' >View items that need to be ordered</a>");

        out.close();
    }
}
