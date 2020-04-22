package data;

import org.postgresql.util.PSQLException;

import java.util.List;
/**
 * Bank ATM users DAO interface to direct implementing class specific Data operations with database
 */
public interface IBankUsers<T, ID>{
    boolean authenticate (String email, String pinNumber);
    T findByEmail(String email);
    List<T> findAll();
    boolean save (T obj, String field);
    boolean insert(T newObj) ;

}
