package com.ex;

abstract public class CRUD {
    abstract public void create(TestEntity testEntity, String filePath);
    abstract public TestEntity read(String filePath);
//    abstract public void update(TestEntity testEntity, String filePath);
//    abstract public void delete(TestEntity testEntity, String filePath);
}
