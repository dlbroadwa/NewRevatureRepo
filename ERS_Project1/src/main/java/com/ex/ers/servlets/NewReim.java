package com.ex.ers.servlets;

import com.ex.ers.models.Person;
import com.ex.ers.services.ReimbursementService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Type;

@WebServlet("/NewReim")
public class NewReim extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReimbursementService service = new ReimbursementService();
        HttpSession session = req.getSession();
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json;charset=UTF-8");

        Object person = req.getAttribute("seshUser");
        String jsonString = new Gson().toJson(person);
        JsonElement element = new Gson().fromJson(jsonString, JsonElement.class);
        JsonObject obj = element.getAsJsonObject();
        Float amount = Float.valueOf(req.getParameter("amount"));
        String comment = req.getParameter("comment");

        service.saveNewReimReq(obj, amount, comment);

        resp.sendRedirect("menu.html");

    }
}
