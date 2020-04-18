package com.proj.data;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T, ID> {
    T findById(ID id);
    List<T> findAll() throws SQLException;
    String save(T obj) throws SQLException;
    void update(T newObj, ID id);
    void delete(T obj);
}