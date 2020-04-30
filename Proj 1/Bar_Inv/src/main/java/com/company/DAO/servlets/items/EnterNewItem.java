package com.company.DAO.servlets.items;

import com.company.DAO.models.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class EnterNewItem extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String createTable= "<table border='1' width='100%'>";
        String tableHeader= "<tr><th>Item Name</th><th>Item ID</th><th>Number in Stock</th>"+
                "<th>Low Level</th><th>Optimal Level</th></tr>";


        out.print("<h2>Add New Inventory Item</h2>");

        out.print("<form action='SaveItemServlet' method='post'>"); //send this info to the save item servlet
        out.print(createTable);
        out.print(tableHeader);
        out.print("<tr><td><input type='text' name='itemName' placeholder='Item Name'/></td>"+
                "<td name='id' type='number' placeholder='ID Number'></td><td>" +
                "<input type='number' name='onHand' placeholder='Number in Stock'/></td>"+
                "<td><input type='number' name='lowLevel' placeholder='Number you shouldn't have less than'/></td>"+
                "<td><input type='number' name='optLevel' placeholder='Number you usually want'/></td>"+
                "</tr>");
        out.print("</table>");
        out.print("<input value='Save New Item' type='submit'/>");
        out.print("</form>");


        out.close();
    }

}
