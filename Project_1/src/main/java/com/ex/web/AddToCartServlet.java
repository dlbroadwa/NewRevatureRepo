package com.ex.web;

import com.ex.data.AddedCartInfo;
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

public class AddToCartServlet extends HttpServlet {
    private ProductService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();
        service = (ProductService)context.getAttribute("productService");
    }

    // This is for when add_to_cart.html retrieves the information about the product that was just added to the cart
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prodID = req.getParameter("pid");

        int pID;
        try {
            pID = Integer.parseInt(prodID);
        }
        catch (NumberFormatException ex) {
            return;
        }
        Product prod = service.getProductByID(pID);
        HttpSession session = req.getSession(false);
        Cart cart = null;
        if (prod != null && session != null && (cart = (Cart)session.getAttribute("shopping-cart")) != null) {
            // Get output as JSON
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(new AddedCartInfo(prod.getName(), cart.getSubtotal()));

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print(json);
            writer.flush();
        }
    }

    // This is for actually adding a product to the cart
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String prodID = req.getParameter("prodID");
        String purchaseQty = req.getParameter("qtyToBuy");

        int pID, qty;

        try {
            pID = Integer.parseInt(prodID);
            qty = Integer.parseInt(purchaseQty);
        }
        catch (NumberFormatException ex) {
            resp.sendError(400, "Invalid parameters");
            return;
        }
        Product prod = service.getProductByID(pID);

        HttpSession session = req.getSession();
        if (session.isNew()) {
            System.out.println("Created new session");
        }
        else
            System.out.println("Welcome back " + session.getId());
        // Create shopping cart if it doesn't already exist
        Cart cart = (Cart)session.getAttribute("shopping-cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("shopping-cart", cart);
        }
        cart.addToCart(prod, qty);
        cart.validate(service);

        String url = "add_to_cart.html?pid=" + pID;
        resp.sendRedirect(resp.encodeRedirectURL(url));
        /*PrintWriter writer = resp.getWriter();
        writer.println("Added to cart: " + prod.getName());
        writer.println("Current cart contents:");
        for (Product p: cart.getContents()) {
            writer.println("Name: " + p.getName() + "  Qty: " + p.getQty());
        }

        writer.flush();*/
    }
}
