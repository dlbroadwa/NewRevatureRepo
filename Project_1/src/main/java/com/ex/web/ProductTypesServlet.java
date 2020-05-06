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
 * ProductTypesServlet Created By:Paityn Maynard on May 4,2020
 */

public class ProductTypesServlet extends HttpServlet {//Start of ProductTypesServlet
//Instant Variables
    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
            "postgres","revature","project1");
    GenericDAO<Product,Integer>products = new ProductSQLDatabase(connectionUtils);
    List<Product> productList,typedProducts;
    String type;
    int price,dollars,cents;

//Methods
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//Start of doGet Method
       StringBuilder httpResponse = new StringBuilder();
       productList = new ArrayList<>();
       typedProducts=new ArrayList<>();
       PrintWriter out = response.getWriter();
        type = request.getParameter("types");
            productList= products.findAll();
        for(Product p : productList){//Start of first for loop
            if(type.equals(p.getProductType())){//Start of first if statement
                typedProducts.add(p);
            }//End of first if statement
        }//End of first for loop

        httpResponse.append( "<html>"
                + "<head><title>"+type+"</title>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>"
                + "<body><h1 id=\"welcome\">Revature Pet Store</h1><h2 id=\"mainh2\">"+type+" Products</h2>");
        for(Product p: typedProducts){//Start of second for loop
            price=p.getPrice();
            dollars=price/100;
            cents=price%100;
            httpResponse.append( "<p>"+p.getName()+"<br/>"
                        + "Price: $"+dollars+"."+cents+"<br/></p>");
        }//End of second for loop
    httpResponse.append("</body></html>");
        out.println(httpResponse);
    } //End of doGet Method
}//End of ProductTypesServlet
