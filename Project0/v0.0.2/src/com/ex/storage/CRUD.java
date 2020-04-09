package com.ex.storage;

import com.ex.types.TestEntity;

import java.io.Serializable;

public interface CRUD {
    public void create(Serializable object, String filePath);
    public Object read(String filePath);
//    abstract public void update(TestEntity testEntity, String filePath);
//    abstract public void delete(TestEntity testEntity, String filePath);
}
