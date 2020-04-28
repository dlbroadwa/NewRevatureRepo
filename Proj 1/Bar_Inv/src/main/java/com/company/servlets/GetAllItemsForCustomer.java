package com.company.servlets;

import com.company.DAO.data.ItemRepository;
import com.company.DAO.data.Repository;
import com.company.DAO.models.Item;
import com.company.app.BarInventoryApplication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class GetAllItemsForCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        List<Item> tmp = null; //hmmm... gotta figure this out
        try {
            tmp = ItemRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //create a table and fill it
        //create table and first row with the header
        out.print("<h2>View Inventory</h2>");
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Item Name</th><th>Item ID</th><th>Number in Stock</th></tr>");
        //fill the table
        for (Item i:tmp){
            out.print("<tr><td>"+i.getItemName()+"</td><td>"+i.getId()+"</td><td>"+i.getOnHand()+"</td></tr>");
        }
        out.print("</table>");

        out.close();
    }
}
