package com.ex.web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.InstrumentSQLRepository;
import models.InstrumentModel;
import models.Transactions;
import utils.PostgresConnectionUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class InstrumentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        if (data.get("buy") == null) {


            try {
                String json = null;
                URL src = new URL(data.get("src").getAsString());
                Float price = data.get("price").getAsFloat();
                String upc = data.get("upc").getAsString();
                Integer catid = new Integer(data.get("catid").getAsString());
                String sale = data.get("sale").getAsString();
                String catdes = data.get("catdes").getAsString();
                InstrumentSQLRepository repo = new InstrumentSQLRepository(new PostgresConnectionUtil());
                repo.save(new InstrumentModel(upc, sale, catid, catdes, price, true, src));
                //System.out.println(repo.findById(upc).getSale());
                InstrumentModel temp = repo.findById(upc);
                Map<String, String> options = new LinkedHashMap<>();
                options.put("src", (String.valueOf(temp.getImage_url())));
                options.put("price", (String.valueOf(temp.getPrice())));
                options.put("upc", (String.valueOf(temp.getUPC())));
                options.put("des", (String.valueOf(temp.getCatName())));
                options.put("cat", (String.valueOf(temp.getCat())));
                options.put("sale", (String.valueOf(temp.getSale())));
                options.put("available", String.valueOf(temp.getAvailable()));
                json = new Gson().toJson(options);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
                //System.out.println(json);

            } catch (Exception e) {
                //e.printStackTrace();
            }
        } else if (data.get("buy").getAsString().equals("yes")) {

        }

    }

    //Called for nothing/ no purposes
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    //
    //Called to Update  instrument availability
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        if (data.get("buy") == null)
        {
            try
            {
                String json = null;
                URL src = new URL(data.get("src").getAsString());
                Float price = data.get("price").getAsFloat();
                String upc = data.get("upc").getAsString();
                Integer catid = new Integer(data.get("catid").getAsString());
                String sale = data.get("sale").getAsString();
                String catdes = data.get("catdes").getAsString();
                InstrumentSQLRepository repo = new InstrumentSQLRepository(new PostgresConnectionUtil());
                repo.save(new InstrumentModel(upc, sale, catid, catdes, price, true, src));
                //System.out.println(repo.findById(upc).getSale());
                InstrumentModel temp = repo.findById(upc);
                Map<String, String> options = new LinkedHashMap<>();
                options.put("src", (String.valueOf(temp.getImage_url())));
                options.put("price", (String.valueOf(temp.getPrice())));
                options.put("upc", (String.valueOf(temp.getUPC())));
                options.put("des", (String.valueOf(temp.getCatName())));
                options.put("cat", (String.valueOf(temp.getCat())));
                options.put("sale", (String.valueOf(temp.getSale())));
                options.put("available", String.valueOf(temp.getAvailable()));
                json = new Gson().toJson(options);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
                //System.out.println(json);

            }
            catch (Exception e)
            {
                //e.printStackTrace();
            }
        }
    }
}

