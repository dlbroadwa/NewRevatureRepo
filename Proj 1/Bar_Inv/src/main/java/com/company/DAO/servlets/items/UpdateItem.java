package com.company.DAO.servlets.items;

import com.company.DAO.data.ItemsDAO;
import com.company.DAO.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateItem extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Item i = new Item();
        i.setItemName(req.getParameter("itemName"));
        i.setId(Integer.parseInt(req.getParameter("id")));
        i.setOptLevel(Integer.parseInt(req.getParameter("optLevel")));
        i.setOnHand(Integer.parseInt(req.getParameter("onHand")));
        i.setLowLevel(Integer.parseInt(req.getParameter("lowLevel")));

//        int status = ItemsDAO.update(i);
//        if (status!=0){
//            resp.sendRedirect("GetAllItemsAdmin");
//        }else {
//            out.print("Something went wrong");
//        }

        out.close();

    }
}
