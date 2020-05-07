package dev.repos;

import java.util.ArrayList;

/**
 *  Project 1:<br>
 * <br>
 *  The Repository interface serves as an interface for a generic repository structure and behavior base.
 *  Repository subclasses can be tailored to use their own choice of data/object type to obtain information from
 *    an appropriate database.
 *  Generic T can be replaced by any primary data/obejct type required/expected for a system.
 *  Generic ID can be replaced by the appropriate data type used for a primary key.
 *  With this, the DAO interface can be used by any means to persist data, as explained by Revature associate trainer
 *    August Duet.
 *
 *  <br> <br>
 *  Created: <br>
 *     25 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     25 April 2020, Barthelemy Martinon,    Created class.
 *     										  Added signatures for findById, findAll, save, update and delete methods.
 *     										    (Favored using ArrayList over List due to dynamic sizing.)
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 25 April 2020
 */
public interface Repository<T, ID> {
    T findById(ID id);
    ArrayList<T> findAll();
    ID save(T obj);
    void update(T newObj, ID id);
    void delete(T obj);
}

