package com.proj;

import com.proj.app.EventHandler;

import java.io.IOException;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws IOException, SQLException {

       EventHandler app = new EventHandler();
       app.run();
    }
}
