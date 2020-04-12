package com.ex.app;

import com.ex.storage.JavaFileIO;
import com.ex.types.Item;

public class Test extends Application {
    public Test(String title) {
        super(title);
    }

    private static final String FILE_PATH = "tempData";

    @Override
    public void run() {
        //Create an Item in local memory
        Item item = new Item();
        System.out.println(item.toString());
        Item item3 = new Item("Cheese", 45);
        System.out.println(item3.toString());

        //Permanently store an Item on disk
        JavaFileIO.getInstance().create(item, FILE_PATH);
        JavaFileIO.getInstance().create(item3, FILE_PATH);
        JavaFileIO.getInstance().read(FILE_PATH);
        JavaFileIO.getInstance().read(FILE_PATH);
    }
}
