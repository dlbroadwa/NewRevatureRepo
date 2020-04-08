package com.ex;

import java.io.*;

public class JavaFileIO extends CRUD{
    @Override
    public void create(TestEntity testEntity, String filePath) {
        try {
            java.io.FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            java.io.ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(testEntity);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println(testEntity.toString() + " was written to file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void read(TestEntity testEntity, String filePath) {
//
//    }
//
//    @Override
//    public void update(TestEntity testEntity, String filePath) {
//
//    }
//
//    @Override
//    public void delete(TestEntity testEntity, String filePath) {
//
//    }
}
