package com.ex.dao;

import com.ex.model.Person;
import com.ex.model.Player;

import java.util.List;

public interface PersonDAO {
    public void addPerson(Person person, Boolean isCoach)throws Exception;

    /* Gets list of all players */
    public List<Player> getAllPlayers();

    /* Gets a list of all coaches */
    public List<Person> getAllCoaches();
}
