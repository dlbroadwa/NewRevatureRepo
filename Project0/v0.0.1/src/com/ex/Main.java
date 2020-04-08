package com.ex;

public class Main {

    public static void main(String[] args) {
        //read from file and create localMemoryArray of Entity objects (contains all file entries)

        //read a number in from ConsoleInput

        //check to see if number exists in localMemoryArray of Entity objects

        //attempt to serialize new number into permanent storage

        //testing
        TestEntity testEntity = new TestEntity(20);
        CRUD crud = new JavaFileIO();
        crud.create(testEntity, "nowhere");
    }
}
