package com.ex.DAO;

import com.ex.MainAndMenu.Runner;

import java.util.List;

/*DAO intended to act as an outlet between FileIO and SQL*/

public interface DAO {
    Integer save(Object o);
    Object getById(Integer id);
    void update(Object o, Integer id);
    void delete(Object o);
    List<Object> getAll();

}
