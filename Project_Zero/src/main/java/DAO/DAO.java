package DAO;

// A Dao is used for CRUD operations on data

import java.util.ArrayList;

public interface DAO<T> {
    /**
     * Saves the current object to persistent storage.
     * @param obj
     * @return
     */
   Integer save(T obj);
   ArrayList<T> retrieveAll(T obj);
   T retrieve(T obj);
   void delete(T obj);

}
