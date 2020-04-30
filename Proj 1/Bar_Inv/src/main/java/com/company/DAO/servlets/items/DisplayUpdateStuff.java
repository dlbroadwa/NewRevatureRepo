package com.company.DAO.servlets.items;

import com.company.DAO.data.web.ItemsDAO;
import com.company.DAO.models.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/")
public class DisplayUpdateStuff extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Item i = new Item();
        String createTable= "<table border='1' width='100%'>";
        String tableHeader= "<tr><th>Item Name</th><th>Item ID</th><th>Number in Stock</th>"+
                "<th>Low Level</th><th>Optimal Level</th></tr>";


        int id = Integer.parseInt(req.getParameter("id"));
        out.print("<h2>Update Inventory Item</h2>");
        i = ItemsDAO.findById(id);
        out.print("<form action='UpdateItem' method='post'>"); //send this info to the update item servlet
        out.print(createTable);
        out.print(tableHeader);
        out.print("<tr><td><input type='text' name='itemName' value='"+ i.getItemName()+"/></td>"+
                "<td name='id'>"+id+"</td><td>"+
                "<td><input type='number' name='onHand' value='"+i.getLowLevel()+"/></td>"+
                "<td><input type='number' name='lowLevel' value='"+i.getLowLevel()+"/></td>"+
                "<td><input type='number' name='optLevel' value='"+i.getOptLevel()+"/></td>"+
                "</tr>");
        out.print("</table>");
        out.print("<input value='Save Update' type='submit'/>");
        out.print("</form>");

        out.close();
    }
}
