package com.ex.storage;

import com.ex.types.TestEntity;

import java.io.*;

public class JavaFileIO implements CRUD{

    @Override
    public void create(TestEntity testEntity, String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(testEntity);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println(testEntity.toString() + " was written to file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TestEntity read(String filePath) {
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            TestEntity testEntity = (TestEntity)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println(testEntity.toString() + " was read from file.");
            return testEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//
//    public void update(TestEntity testEntity, String filePath) {
//
//    }
//
//    public void delete(TestEntity testEntity, String filePath) {
//
//    }
}
