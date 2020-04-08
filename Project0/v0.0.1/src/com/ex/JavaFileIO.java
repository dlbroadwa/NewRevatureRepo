package com.ex;

import java.io.*;

public class JavaFileIO extends CRUD{
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
