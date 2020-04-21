package com.ex.DAO;

import java.util.List;
//Set up the "map" of methods to be used in SqlDatabaseAnimals and SqlDatabaseKeepers
public interface DAO<N> {
    List<N> findAll();
    List<N> specificFind();
    void save(N object);
    void delete(N object);
}
