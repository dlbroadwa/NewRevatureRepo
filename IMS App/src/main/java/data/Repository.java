package data;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T, ID>
{
    T findById(ID id);
    List<T> findAll() throws SQLException;
    ID save(T obj);
    void update(T newObj, ID id);
    void delete(T obj);
}
