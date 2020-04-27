/**
 * The Repository Inteface acts as a general template for the applied models
 * This interface is a generic that takes in a Type of the implementors choice
 * and an Integer Object.
 * functions:
 *findById(int i): function to be implemented of type Type (specified by implementor)
 * @params integer value
 *findAll(): function of a specified type that searches through the list of that type.
 * @params may throw an SQLException
 * update(): function template that updates the repository of the database.
 * delete(): function template that deletes an item in the repository.
 * NOTE: functions must be defined when implemented
 */
package data;

// Gets necessary Classes for testing and collecting objects within a list
import java.sql.SQLException;
import java.util.List;

public interface Repository<T, Integer>
{
    //T findById(ID id);
    // Template for finding an instrument in the database through it's id
    T findById(int i);
    // Template for getting all the items within the database
    List<T> findAll() throws SQLException;
    //ID save(T obj);
    //void update(T newObj, ID id);
    // Template for adding to a database
    void update();
    //void delete(T obj);
    // Template for deleting from a database
    void delete();
}
