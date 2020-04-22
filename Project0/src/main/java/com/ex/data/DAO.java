package main.java.com.ex.data;

/**
 * 
 * DAO: an interface that allows for data CRUD operations for various data repositories
 * 
 * @author jtb12_000
 *
 * @param <T>: the object being retrieved from the data repository
 * @param <ID>: the method of identifying which data to retrieve
 */

public interface DAO<T,ID> {
	T findById(ID id);
	void save(T obj);
	void update(T newObj, ID id);
	void delete(ID id);
}
