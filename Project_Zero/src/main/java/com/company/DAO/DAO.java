package com.company.DAO;

// A Dao is used for CRUD operations on data

import org.postgresql.util.PSQLException;

import java.util.ArrayList;

public interface DAO<T, ID> {
   public static final Integer OPERATION_FAILED = null;

    /***
     * Saves the current object to persistent storage.
     * @param obj
     * @return
     */
   ID save(T obj) throws PSQLException;

   /***
    * This method returns the all the objects of type T from persistent storage.
    * @return
    * @throws PSQLException
    */
   ArrayList<T> retrieveAll() throws PSQLException;

   /***
    * This method retrieves an object of type T from persistent storage identified by the value passed which is of type ID.
    * @param id
    * @return
    * @throws PSQLException
    */
   T[] retrieveByID(ID id) throws PSQLException;

   /***
    * This method deletes all objects of type T in persistent storage.
    * @param obj
    * @throws PSQLException
    */
   void delete(T obj) throws PSQLException;

   /***
    * This method updates the object of type T in persistent storage.
    * @param newObj
    * @throws PSQLException
    */
   void update(T newObj) throws PSQLException;

}
