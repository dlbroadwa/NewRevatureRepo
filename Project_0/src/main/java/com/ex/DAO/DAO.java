package com.ex.DAO;

import java.util.List;

public interface DAO<N> {
    List<N> findAll();
    List<N> specificFind();
    void save(N object);
    void delete(N object);
}
