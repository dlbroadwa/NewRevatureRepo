package com.Project0;
//***************************************************************************//
import java.util.List;
//***************************************************************************//

public interface Repository<T,ID>
{
    T findbyID(ID var1); //All repo's will have a unique ID.
    List<T> findAll();  //A list of all items in the repo should be available, consider static.
    ID save(T var1);    //Saving an item into the DB, similar to update.
    void update(T var1, ID var2);   //Update the DB and Repo with equivalent information on each iteration, save + finAll.
    void delete(ID var1); //Remove an item from a Repo and DB.
//***************************************************************************//
}
