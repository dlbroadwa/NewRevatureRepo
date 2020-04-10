package DAO;

// A Dao is used for CRUD operations on data

public interface DAO<T> {
    /**
     * Saves the current object to persistent storage.
     * @param obj
     * @return
     */
   Integer save(T obj);
   Integer retrieveAll(T obj);
   Integer retrieve(T obj);
}
