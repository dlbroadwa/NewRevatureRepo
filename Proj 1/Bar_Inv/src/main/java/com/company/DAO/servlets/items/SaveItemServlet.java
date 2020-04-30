package com.company.DAO.servlets.items;

import com.company.DAO.data.ItemsDAO;
import com.company.DAO.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveItemServlet extends HttpServlet {
    ItemsDAO itemsDAO = ItemsDAO.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String itemName=request.getParameter("itemName");
        int id = Integer.parseInt(request.getParameter("id"));
        int onHand = Integer.parseInt(request.getParameter("onHand"));
        int lowLevel = Integer.parseInt(request.getParameter("lowLevel"));
        int optLevel = Integer.parseInt(request.getParameter("optLevel"));

        Item i=new Item();
        i.setItemName(itemName);
        i.setId(id);
        i.setOnHand(onHand);
        i.setLowLevel(lowLevel);
        i.setOptLevel(optLevel);

        int status = itemsDAO.save(i);

        if(status>0){
            out.print("<p>Item was added to the online database.</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }else{
            out.println("An error occurred, and the item was not saved to the online database.");
        }

        out.close();
    }

}
