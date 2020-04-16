package com.company.DAO.data;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T,ID> {
    //actions I want to be able to do to each table
    T findByID (ID id);
    List<T> findAll() throws SQLException;
    void save(T obj);
    void deleteByID(ID id);
    void updateByID(ID id);
}
