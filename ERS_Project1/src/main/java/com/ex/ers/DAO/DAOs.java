package com.ex.ers.DAO;

import java.util.List;

public interface DAOs<T> {
    T findByName (String s);
    T findByID (int id);
    List<T> findAll();
    List<T> findAllForName(String s);
    int save(T obj);
    int update(int id);

}
