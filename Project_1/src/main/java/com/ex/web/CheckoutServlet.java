package com.ex.web;

import com.ex.data.GenericDAO;
import com.ex.models.Account;
import com.ex.models.Cart;
import com.ex.models.Order;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckoutServlet extends HttpServlet {
    private GenericDAO<Order, Integer> orderDao; // TODO abstract away

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();
        orderDao = (GenericDAO<Order, Integer>)context.getAttribute("orderDAO");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.html?redirect=doCheckout");
        }
        else {
            resp.sendRedirect("checkout.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Account user;
        if (session == null || (user = (Account)session.getAttribute("user")) == null) {
            resp.sendRedirect("login.html?redirect=doCheckout");
        }
        else {
            Cart cart = (Cart)session.getAttribute("shopping-cart");
            // Can't check out with an empty cart
            if (cart == null || cart.getContents().isEmpty()) {
                resp.sendRedirect("index.html");
            }
            else {
                // Process order
                Order newOrder = new Order(0, user, cart.getContents(), Order.Status.PROCESSING);
                // Get a (hopefully unique) confirmation number
                int confirmation = (int) (newOrder.hashCode() ^ (System.currentTimeMillis() % 19539761));
                if (confirmation < 0)
                    confirmation = -confirmation; // I hope this doesn't overflow?
                newOrder.setOrderConfirmation(confirmation);

                if (orderDao.add(newOrder)) {
                    resp.sendRedirect("yay.html?orderid=" + newOrder.getOrderConfirmation());
                }
                else {
                    // Back to the checkout page, I guess...?
                    resp.sendRedirect("checkout.html");
                }
            }
        }
    }
}
