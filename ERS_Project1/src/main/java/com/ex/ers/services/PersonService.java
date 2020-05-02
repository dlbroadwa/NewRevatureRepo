package com.ex.ers.services;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.models.Person;

import java.util.List;

public class PersonService {
    private PersonDAO personDAO;
    public PersonService(){
        this.personDAO = new PersonDAO();
    }

    public int legitName(String s){
        Person person = null;
        person = this.personDAO.findByName(s);
        System.out.println(person.getUsername());
        try{
            if(person.getUsername().equals(s)){
                return 1;
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int loginPerson(String username, String password){
        Person person = null;
        person = this.personDAO.findByName(username);
        System.out.println(person.getUsername());

        if(password.equals(person.getPw())){
            return 1;
        } else {
            return 0;
        }
    }

    public List<Person> getAllEmployees(){
        List<Person> everyone = null;
        everyone = this.personDAO.findAll();
        return everyone;
    }
}
