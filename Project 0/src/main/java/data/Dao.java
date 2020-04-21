package data;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

// Universal DAO interface for implementation
public interface Dao<T, ID> {
    T findById(ID id);
    List<T> findAll();
    ID save(T obj);
    void update(T newObj, ID id);
    void delete(T obj);
}
