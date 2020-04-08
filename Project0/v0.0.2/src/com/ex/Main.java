package com.ex;

import com.ex.console.ConsoleInput;
import com.ex.storage.JavaFileIO;
import com.ex.types.TestEntity;

public class Main {

    public static void main(String[] args) {
        //read from file and create localMemoryArray of Entity objects (contains all file entries)

        //read a number in from ConsoleInput

        //check to see if number exists in localMemoryArray of Entity objects

        //attempt to serialize new number into permanent storage

        //starter testing
        ConsoleInput consoleInput = null;
        ConsoleInput.initialize();
        TestEntity testEntity = new TestEntity(consoleInput.getNumber());
        JavaFileIO javaFileIO = new JavaFileIO();
        javaFileIO.create(testEntity, "nowhere");
        TestEntity testEntity1 = javaFileIO.read("nowhere");
        System.out.println(testEntity1.toString());
    }
}
