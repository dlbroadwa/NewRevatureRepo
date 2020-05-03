package com.ex.service;

import com.ex.dao.PersonDAO;
import com.ex.dao.PersonDAOImpl_PGR;
import com.ex.model.Person;
import com.ex.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    private PersonDAO personDAO;

    public PersonService(){
        this.personDAO = new PersonDAOImpl_PGR();
    }

    /**
     * This is specifically for tests - where we pass in a DAO to mock
     * @param personDAO
     */
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
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

    /* Gets list of all players */
    public List<Player> getAllPlayers(){
        List<Player> players = new ArrayList<>();
        try{
            players = personDAO.getAllPlayers();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return players;
        }
    }

    /* Gets a list of all coaches */
    public List<Person> getAllCoaches() {
        List<Person> coaches = new ArrayList<>();
        try{
            coaches = personDAO.getAllCoaches();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return coaches;
        }
    }
}
