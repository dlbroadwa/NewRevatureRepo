package com.company.DataAccess;

import java.util.List;

public interface DAO<T,ID> {
    T findById(ID id);
    List<T> findAll();
    ID save(T obj);
    void update(T newObj, ID id);
    void delete(T obj);
}
