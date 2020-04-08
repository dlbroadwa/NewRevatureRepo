package com.ex;

public interface CRUD {
    public void create(TestEntity testEntity, String filePath);
    public TestEntity read(String filePath);
//    abstract public void update(TestEntity testEntity, String filePath);
//    abstract public void delete(TestEntity testEntity, String filePath);
}
