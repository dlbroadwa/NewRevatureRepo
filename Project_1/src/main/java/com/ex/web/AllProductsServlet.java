package com.ex.web;

import com.ex.data.GenericDAO;
import com.ex.data.ProductSQLDatabase;
import com.ex.models.Product;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
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
 */
public class AllProductsServlet extends HttpServlet {//Start of AllProductsServlet
//Instance Variables
DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
        "postgres","revature","project1");
    GenericDAO<Product,Integer> products = new ProductSQLDatabase(connectionUtils);
    List<Product> productList;
    int price,dollars,cents;
//Methods
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//Start of service Method
        productList=new ArrayList<>();
        PrintWriter out = response.getWriter();
        StringBuilder httpResponse = new StringBuilder();
            httpResponse.append("<body><form action =\"cart\" method=\"get\">");
        productList=products.findAll();
        for(Product p:productList){
            price=p.getPrice();
            dollars=price/100;
            cents=price%100;
            httpResponse.append("<p>"+p.getName()+"Price: $"+dollars+"."+cents+"<br/>Type:"+p.getProductType()+"</p>");
                                 //  +"<input type=\"submit\" value=\"\">");  HOW TO SET VALUE IN A WAY TO CALL IT LATER
        }
        httpResponse.append("</form></body>");
        out.println(httpResponse);
    }//End of doGet Method
}//End of AllProductsServlet
