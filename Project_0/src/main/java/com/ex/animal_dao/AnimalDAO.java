package com.ex.animal_dao;

import java.util.List;

public interface AnimalDAO<N, S, G, A, E> {

    List<N> findAll();
    void save(N animal);
    void delete(N animal);
}
