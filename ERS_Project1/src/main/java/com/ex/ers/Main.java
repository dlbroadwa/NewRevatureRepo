package com.ex.ers;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.app.Application;
import com.ex.ers.app.ERSApp;
import com.ex.ers.models.Person;
import com.ex.ers.services.PersonService;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;

public class Main {
    public static void main(String[] args) throws Exception {
        Application app;
        app = new ERSApp();
        app.run();


    }
}
