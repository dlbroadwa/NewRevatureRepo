package com.game.data;

import java.util.List;

// DAO interface can be used by any means to persist data
public interface Repository<T, ID> {
    T findById(ID id);
    List<T> findAll();
    ID save(T obj);
    void update(T newObj, ID id);
    void delete(T obj);
}

