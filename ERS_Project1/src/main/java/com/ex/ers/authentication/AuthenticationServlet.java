package com.ex.ers.authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.toString());
        System.out.println("doPost was called");

        StringBuilder sb1 = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb1.append(line).append('\n');
            }
        } finally {
            reader.close();
        }
        System.out.println("sb attempt" + sb1.toString());

//        Collection<Part> coll = req.getParts();
//        for(Part p: coll){
//            System.out.println(p.getName() + " " + p.toString());
//        }
//        Map<String, String[]> userInfo = request.getParameterMap();
//        for (Map.Entry<String, String[]> entry : userInfo.entrySet()) {
//            System.out.println(entry.getKey() + "/" + Arrays.toString(entry.getValue()));
//        }
//        System.out.println(request.getParameter("username"));
//        System.out.println(request.getParameter("password"));
//
//        Enumeration<String> headerNames1 = request.getHeaderNames();
//        while (headerNames1.hasMoreElements()) {
//            String headerName = headerNames1.nextElement();
//            System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
//        }
//
//        Enumeration<String> params = request.getParameterNames();
//        while (params.hasMoreElements()) {
//            String paramName = params.nextElement();
//            System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//        }
//
//        super.doPost(request, response);
//
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
//        }
//
//        Enumeration<String> params2 = request.getParameterNames();
//        while (params.hasMoreElements()) {
//            String paramName = params2.nextElement();
//            System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//        }
        /*
    getParameter() − You call request.getParameter() method to get the value of a form parameter.
    getParameterValues() − Call this method if the parameter appears more than once and returns multiple values, for example checkbox.
    getParameterNames() − Call this method if you want a complete list of all parameters in the current request.
         */
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req);
        System.out.println("doGet was called");
        super.doPost(req, resp);
    }
}
