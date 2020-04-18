package com.company.DAO;

// A Dao is used for CRUD operations on data

import org.postgresql.util.PSQLException;

import java.util.ArrayList;

public interface DAO<T, ID> {
   public static final int OPERATION_FAILED = -1;
    /**
     * Saves the current object to persistent storage.
     * @param obj
     * @return
     */
   ID save(T obj) throws PSQLException;
   ArrayList<T> retrieveAll() throws PSQLException;
   T[] retrieveByID(ID id) throws PSQLException;
   void delete(T obj) throws PSQLException;
   void update(T newObj) throws PSQLException;

}
