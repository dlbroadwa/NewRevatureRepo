package com.ex.ers.services;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.models.Person;

import java.util.List;

public class PersonService {
    private PersonDAO personDAO;
    public PersonService(){this.personDAO = new PersonDAO();}
    public PersonService(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    public int legitName(String s){
        Person person = null;
        person = this.personDAO.findByName(s);
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
        if (password.equals(person.getPw())){
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

    public int saveNewUser(String fname, String lname, String address, String jobtitle, String username, String pw){
        int status = 0;
        Person person = new Person();
        if(this.personDAO.findByName(username)==null){            //username not in use
            person.setFname(fname);
            person.setLname(lname);
            person.setAddress(address);
            person.setJobTitle(jobtitle);
            person.setUsername(username);
            person.setPw(pw);
            this.personDAO.save(person);
            status = 1;
        }

        return status;
    }
}
