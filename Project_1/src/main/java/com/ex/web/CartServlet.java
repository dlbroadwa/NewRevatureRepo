package com.ex.web;


import com.ex.models.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CartServlet Created By:Paityn Maynard on May 5,2020
 */

public class CartServlet extends HttpServlet {//Start of CartServlet Class
//Instance Variables
HttpSession session;
String productId;
List<Product> products = new ArrayList<>();
Product product;
//Methods
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{//Start of doGet Method
        session=request.getSession(true);

    }//End of doGet Method

}//End of CartServlet Class
