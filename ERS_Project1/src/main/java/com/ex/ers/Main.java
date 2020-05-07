package com.ex.ers;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.app.Application;
import com.ex.ers.app.ERSApp;
import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.services.PersonService;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;
import com.google.gson.Gson;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Application app;
        app = new ERSApp();
        app.run();

        PersonService service = new PersonService();
        String username = "johncrevature";
        String password = "pass";
        Person check = service.loginPerson(username, password);
        if (check != null) {
            // make and send json object
            String personJsonString = new Gson().toJson(check);
            boolean manager = check.isManager();
            System.out.println(personJsonString);

        }
    }
}