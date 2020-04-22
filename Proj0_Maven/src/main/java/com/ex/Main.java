package com.ex;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://database-1.c7zjtw5vhjwr.us-east-2.rds.amazonaws.com:5432/postgres";
        String username = System.getenv("LIBRARY_ADMIN_USER");
        String password = System.getenv("LIBRARY_ADMIN_PASSWORD");

        DatabaseConnection connection = new PostgreSQLConnection(url, username, password, "project0");

        if (!connection.isDriverInitialized()) {
            System.err.println("Failed to initialize database driver!");
            return;
        }

        Application app = new LibraryApp(connection);
        app.run();
    }
}
