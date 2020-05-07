package com.ex.web;

import com.ex.data.AccountSQLDatabase;
import com.ex.data.GenericDAO;
import com.ex.data.OrderSQLDatabase;
import com.ex.data.ProductSQLDatabase;
import com.ex.models.Account;
import com.ex.models.Order;
import com.ex.models.Product;
import com.ex.services.ProductService;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * Created by Perry Lee on May 5,2020
 */

public class AppContextListener implements ServletContextListener {
// Data access layer
    private DatabaseConnection dc = null;
    private GenericDAO<Account, String> accountDao = null;
    private GenericDAO<Order, Integer> orderDao = null;
    private GenericDAO<Product, Integer> productDao = null;

// Service layer
    private ProductService productService = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        System.out.println("Servlet context initialized");

        // Initialize database connection
        String url = "jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres";
        String username = "postgres";
        String password = "revature";

        dc = new PostgreSQLConnection(url, username, password, "project1");
        accountDao = new AccountSQLDatabase(dc);
        orderDao = new OrderSQLDatabase(dc);
        productDao = new ProductSQLDatabase(dc);

        productService = new ProductService(productDao);

        // TODO abstract these three away
        context.setAttribute("accountDAO", accountDao);
        context.setAttribute("orderDAO", orderDao);
        context.setAttribute("productDAO", productDao);

        context.setAttribute("productService", productService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servlet context destroyed");
    }
}
