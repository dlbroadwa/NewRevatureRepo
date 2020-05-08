package com.ex.web;


import com.ex.models.Cart;
import com.ex.models.Product;
import com.ex.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * CartServlet Created By:Paityn Maynard on May 5,2020
 */

public class CartServlet extends HttpServlet {//Start of CartServlet Class
//Instance Variables
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();
        productService = (ProductService)context.getAttribute("productService");
    }

    //Methods
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{//Start of doGet Method
        HttpSession session = req.getSession(true);
        if (session.isNew() || session.getAttribute("shopping-cart") == null) {
            System.out.println("Creating new shopping cart for session " + session.getId());
            session.setAttribute("shopping-cart", new Cart());
        }

        Cart cart = (Cart)session.getAttribute("shopping-cart");
        cart.validate(productService);

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(cart);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }//End of doGet Method

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Cart cart = null;
        if (session != null && (cart = (Cart)session.getAttribute("shopping-cart")) != null) {
            cart.clear();
        }
    }
}//End of CartServlet Class
