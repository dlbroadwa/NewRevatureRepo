package com.ex.web;

import com.ex.data.GenericDAO;
import com.ex.data.OrderSQLDatabase;
import com.ex.models.Order;
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

public class ViewOrderServlet extends HttpServlet {
//Instance Variables
    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
            "postgres","revature","project1");
    GenericDAO<Order,Integer> orders = new OrderSQLDatabase(connectionUtils);
    Order order;
    String idS;
    int id;
    StringBuilder httpResponse;
    List<Product> productList;

//Methods
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        httpResponse = new StringBuilder();
        productList = new ArrayList<>();
            httpResponse.append("<html><head><title>Logged Out</title><link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>"
                    +"<body> <h1 id=\"welcome\">Revature Pet Store</h1><h2>");
        PrintWriter out = response.getWriter();
       idS=request.getParameter("orderId");
       id = Integer.parseInt(idS);
       order=orders.findByID(id);

       if(order==null){
           httpResponse.append("Order not Found");
       }
       else{
           productList = order.getOrderProducts();
           httpResponse.append("Order: " + id +"</h2><br/>Status: "+order.getStatus()+"<br/> Products:");
           for(Product p : productList){
               httpResponse.append("<br/> Name: "+p.getName()+" Type "+ p.getProductType()+ " Quantity: "+ p.getQty());
           }
       }
       out.println(httpResponse);
    }

}
