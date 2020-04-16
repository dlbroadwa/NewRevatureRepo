package com.proj.data;

import java.util.List;

public interface Repository<T, ID> {
    T findById(ID id);
    List<T> findAll();
    ID save(T obj);
    void update(T newObj, ID id);
    void delete(T obj);
}