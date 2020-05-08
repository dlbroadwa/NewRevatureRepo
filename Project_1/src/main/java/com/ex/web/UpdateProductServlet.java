package com.ex.web;

import com.ex.data.GenericDAO;
import com.ex.data.ProductSQLDatabase;
import com.ex.models.Product;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Paityn Maynard on May 7,2020
 */
public class UpdateProductServlet extends HttpServlet {//Start of UpdateProductServlet class
//Instance Variables
    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
            "postgres","revature","project1");
    GenericDAO<Product,Integer> products = new ProductSQLDatabase(connectionUtils);
    StringBuilder httpResponse;
    Product product = new Product();
    String name, qtyS;
    int qty, oldQ,id;
    boolean complete;
//Methods
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {//Start of doGet method
        httpResponse=new StringBuilder();
            httpResponse.append("<html><head><title>Update Confirmation</title><link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>\""
                    +"<body> <h1 id=\"welcome\">Revature Pet Store</h1>");
        PrintWriter out = response.getWriter();
        name = request.getParameter("name");
        qtyS = request.getParameter("qty");
            qty=Integer.parseInt(qtyS);
        for(Product p : products.findAll()){
            if(p.getName().equals(name)){
                id=p.getProductID();
            }
        }
           product = products.findByID(id);
           oldQ=product.getQty();
           qty = qty + oldQ;

           product.setQty(qty);
          complete = products.update(product.getProductID(),product);
            if(complete){
                httpResponse.append("<h2 id=\"mainh2\">Product Name: "+product.getName()+"</h2><p>Quantity from "+oldQ+" to "+qty+"</p><br/>" +
                        "<a class=\"button\" href=\"sessionCheck\">Revature Employee Portal</a>" +
                        "<a class=\"button\" href=\"index.html\">Back to Home Page</a></body>");
                out.println(httpResponse);
            }

        out.flush();
    }//End of doGet method
}//End of UpdateProductServlet class
