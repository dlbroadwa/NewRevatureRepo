package com.data;

import java.util.List;

public interface Repo <D, ID>{
    D findById(ID id);
    List<D> findAll();
    ID save(D obj);
    void update(D newObj, ID id);
    void delete(D obj);
}
