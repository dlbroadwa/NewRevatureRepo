package com.ex.data;

/**
 * Interface representing a generic data access object.
 * @param <T> The type of data being manipulated
 * @param <ID> The type of the primary key of the data
 */
public interface GenericDAO<T, ID> {
    /**
     * Adds a new object to the database.
     * @param newObj The object to be added
     * @return <code>true</code> if the object was successfully added, <code>false</code> otherwise.
     */
    boolean add(T newObj);

    /**
     * Finds and returns the object with the specified ID.
     * @param id the ID of the object to find
     * @return the object with that ID, or <code>null</code> if such an object could not be found.
     */
    T findByID(ID id);

    /**
     * Updates the object with the specified ID.
     * @param id the ID of the object to be updated
     * @param newObj the new object that will replace the existing object in the database
     * @return <code>true</code> if an update occurred successfully, <code>false</code> otherwise.
     */
    boolean update(ID id, T newObj);

    /**
     * Removes the object with the specified ID.
     * @param id the ID of the object to remove
     * @return <code>true</code> if a deletion occurred successfully, <code>false</code> otherwise.
     */
    boolean remove(ID id);
}
