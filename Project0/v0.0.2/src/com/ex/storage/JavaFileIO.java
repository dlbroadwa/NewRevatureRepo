package com.ex.storage;

import com.ex.types.TestEntity;

import java.io.*;

public class JavaFileIO implements CRUD{
    @Override
    public void create(Serializable object, String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println(object.toString() + " was written to file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object read(String filePath) {
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println(object.toString() + " was read from file.");
            return object;
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
