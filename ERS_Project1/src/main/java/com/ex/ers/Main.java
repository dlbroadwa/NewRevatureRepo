package com.ex.ers;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.app.Application;
import com.ex.ers.app.ERSApp;
import com.ex.ers.models.Person;
import com.ex.ers.services.PersonService;
import com.ex.ers.services.ReimbursementService;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;

public class Main {
    public static void main(String[] args) throws Exception {
        Application app;
        app = new ERSApp();
        app.run();

        ReimbursementService service = new ReimbursementService();
        PersonService personService = new PersonService();


        int id = 18;
        Person person = new Person();
        person = personService.findById(id);
        String requester = person.getFname()+" "+person.getLname();
        Float amount = 600f;
        String comment = "comment";

        service.saveNewReimReq(requester, amount, comment);

        System.out.println(person);



    }
    }
