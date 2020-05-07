package com.ex.web;

import com.ex.data.GenericDAO;
import com.ex.data.ProductList;
import com.ex.data.ProductSQLDatabase;
import com.ex.models.Product;
import com.ex.services.ProductService;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * AllProductsServlet Created By:Paityn Maynard on May 2,2020
 * Perry Lee: Added init method and changed doGet method-May 6
 */
public class AllProductsServlet extends HttpServlet {//Start of AllProductsServlet
//Instance Variables
    private ProductService prodService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();
        prodService = (ProductService)context.getAttribute("productService");
    }

    //Methods
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//Start of doGet Method
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        List<Product> products = prodService.getAllProducts();
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(new ProductList(products));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();

        /*Paityn Maynard's Code
        StringBuilder httpResponse = new StringBuilder();
        httpResponse.append("<body><form action =\"cart\" method=\"get\">");
        List<Product> productList = prodService.getAllProducts();

        for(Product p: productList) {//Start of for loop
            int price = p.getPrice();
            int dollars = price / 100;
            int cents = price % 100;
            httpResponse.append("<p>"+p.getName()+"<br/>Price: $"+dollars+"."+cents+"<br/>Type:"+p.getProductType()+"</p>");
                                 //  +"<input type=\"submit\" value=\"\">");  HOW TO SET VALUE IN A WAY TO CALL IT LATER
        }//End of for loop
        httpResponse.append("</form></body>");
        out.println(httpResponse);*/
    }//End of doGet Method
}//End of AllProductsServlet
