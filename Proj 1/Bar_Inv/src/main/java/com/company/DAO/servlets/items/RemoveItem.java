package com.company.DAO.servlets.items;

import com.company.DAO.data.web.ItemsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class RemoveItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PrintWriter out=resp.getWriter();

        int status = ItemsDAO.delete(id);
        if(status==0){
            out.println("Something went wrong, couldn't delete");
        }else{
            //what do we want to show when we delete something?
            resp.sendRedirect("GetAllItemsAdmin");
        }
    }
}
