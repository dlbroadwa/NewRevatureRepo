package com.company.DAO.data;

import com.company.DAO.models.Item;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveItemServlet")
public class SaveItemServlet extends HttpServlet {
    TemporaryServletDAO temporaryServletDAO = TemporaryServletDAO.getInstance();

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

        int status = temporaryServletDAO.save(i);

        if(status>0){
            out.print("<p>Item was added to the online database.</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }else{
            out.println("An error occurred, and the item was not saved to the online database.");
        }

        out.close();
    }

}
