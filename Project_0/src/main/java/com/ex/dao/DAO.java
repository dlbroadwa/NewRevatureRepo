package com.ex.dao;

import java.lang.reflect.Array;
import java.util.List;

public interface DAO<N, S, G, A, E> {

    List<N> findAll();
    S save(N animal);
    void update(N newName, S species, G sex, A age, E enclosure);
     void delete(N name);

}
