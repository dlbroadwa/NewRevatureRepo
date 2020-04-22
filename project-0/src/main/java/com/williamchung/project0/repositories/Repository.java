package com.williamchung.project0.repositories;

import java.util.List;

//Interface for Repositories with Generic types
public interface Repository<T, ID> {
  T findById(ID id);
  List<T> findAll();
  ID save(T obj);
  void update(T newObj, ID id);
  void delete(T obj);
}