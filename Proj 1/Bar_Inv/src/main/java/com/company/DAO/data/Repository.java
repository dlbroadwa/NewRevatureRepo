package com.company.DAO.data;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T,ID, S> {
    //actions I want to be able to do to each table
    T findByID (ID id);
    List<T> findAll() throws SQLException;
    List<T> findAllForName(ID id) throws SQLException;
    void save(T obj);
    void deleteByID(ID id);
    void updateByID(T obj);
    List<T> compareColumns(S s1, S s2, S s3);

}
