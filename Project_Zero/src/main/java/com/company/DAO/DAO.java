package com.company.DAO;

// A Dao is used for CRUD operations on data

import java.util.ArrayList;

public interface DAO<T, ID> {
    /**
     * Saves the current object to persistent storage.
     * @param obj
     * @return
     */
   ID save(T obj);
   ArrayList<T> retrieveAll();
   T retrieveByID(ID id);
   void delete(T obj);
   void update(T newObj);

}
