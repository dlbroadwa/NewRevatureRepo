package com.ex.dao.animal_dao;

import java.util.List;

public interface AnimalDAO<N, S, G, A, E> {

    List<N> findAll();
    void save(N animal);
    void delete(N animal);
    void update(N newName, S species, G sex, A age, E enclosure);
}
