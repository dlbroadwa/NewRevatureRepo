package com.ex.service;

import com.ex.dao.PersonDAO;
import com.ex.dao.PersonDAOImpl_PGR;
import com.ex.model.Person;

public class PersonService {
    private PersonDAO personDAO;
    public PersonService(){
        this.personDAO = new PersonDAOImpl_PGR();
    }
    public boolean addPerson(Person person, Boolean isCoach) {
        try {
            personDAO.addPerson(person, isCoach);
            return isCoach;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
