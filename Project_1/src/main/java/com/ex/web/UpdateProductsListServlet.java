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

public class UpdateProductsListServlet extends HttpServlet {//Start of AllProductServlet class
//Instance Variables
    DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
        "postgres","revature","project1");
    GenericDAO<Product,Integer> products = new ProductSQLDatabase(connectionUtils);
    String action,name,type, priceS, qtyS;
    int price,qty;
    Product product = new Product();
    boolean complete;
    List<Product> allProducts;
    StringBuilder httpResponse;

//Methods
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        httpResponse=new StringBuilder();
            httpResponse.append("<html><head><title>Invalid Login</title> <link rel=\"stylesheet\" type=\"text/css\" href=\"webDesign.css\"></head>"
                +"<body><h1 id=\"welcome\">Revature Pet Store</h1><h2 id=\"mainh2\">");
        allProducts = new ArrayList<>();

        action= request.getParameter("action");
        name= request.getParameter("name");
        type=request.getParameter("types");
        priceS = request.getParameter("price");
            price = Integer.parseInt(priceS);
        qtyS = request.getParameter("qty");
            qty = Integer.parseInt(qtyS);

        product.setName(name);
        product.setProductType(type);
        product.setPrice(price);

        if(action.equals("add")){
            product.setQty(qty);
                products.add(product);
                httpResponse.append("Successful Add of Product: "+name);
        }
        if(action.equals("update")){
            
        }
        httpResponse.append("</h2><a class=\"button\" href=\"add_product.html\">Update More</a>"
                           + "<a class=\"button\" href=\"pet_store_portal.html\">Back to Portal</a>");
    }//End of doPost method

}//End of AllProductServlet
