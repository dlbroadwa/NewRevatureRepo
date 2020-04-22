package data;

import models.GrossPay;

import java.util.List;

/**
 * Contains universal DAO implementation that can be utilized to access database info. Base DAO inteface provided with assistance by Austin Duet.
 * @param <T>
 * @param <ID>
 */
// Universal DAO interface for implementation
public interface Dao<T, ID> {
    T findById(GrossPay id);
    List<T> findAll();
    void save(T obj);
    void update(T obj);
    void delete(T obj);
}
