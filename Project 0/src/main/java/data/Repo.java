package data;

import java.util.List;

// Universal DAO interface for implementation
public interface Repo<T, ID> {
    T findById(ID id);
    List<T> findAll();
    ID save(T obj);
    void update(T newObj, ID id);
    void delete(T obj);
}
