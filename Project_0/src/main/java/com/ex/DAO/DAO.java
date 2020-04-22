package com.ex.DAO;

import java.util.List;
/*Interface Description
    *Set up the "map" of methods to be used in SqlDatabaseAnimals and SqlDatabaseKeepers
 */
public interface DAO<N> {//Start of DAO Interface
    List<N> findAll();
    List<N> specificFind();
    Integer numberOf();
    Integer specificNumberOf();
    void save(N object);
    void delete(N object);
}//End of DAO Interface
