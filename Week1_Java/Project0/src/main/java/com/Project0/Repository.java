package com.Project0;

import java.util.List;


public interface Repository<T,ID>
{
    T findbyID(ID var1);
    List<T> findAll();
    ID save(T var1);
    void update(T var1, ID var2);
    void delete(ID var1);

}
