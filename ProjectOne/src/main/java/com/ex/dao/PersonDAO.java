package com.ex.dao;

import com.ex.model.Person;

public interface PersonDAO {
    public void addPerson(Person person, Boolean isCoach)throws Exception;
}
