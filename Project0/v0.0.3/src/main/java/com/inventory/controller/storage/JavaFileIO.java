package com.inventory.controller.storage;

import com.inventory.controller.system.ConsoleOut;
import java.io.*;

public class JavaFileIO implements CRUD{
    private JavaFileIO(){}

    private static JavaFileIO javaFileIO;

    public static JavaFileIO getInstance(){
        if(javaFileIO == null){
            javaFileIO = new JavaFileIO();
        }
        return javaFileIO;
    }
    @Override
    public void create(Serializable object, String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
            ConsoleOut.println(object.toString() + " was written to file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Object read(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            Object object = objectInputStream.readObject();
            ConsoleOut.println(object.toString() + " was read from file.");
            return object;
        } catch (Exception e) {
            e.printStackTrace(ConsoleOut.err);
        }
        return null;
    }
}
