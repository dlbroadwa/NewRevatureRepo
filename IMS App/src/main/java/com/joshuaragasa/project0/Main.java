package com.joshuaragasa.project0;

import app.IMSEntry;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException
    {
        IMSEntry app = new IMSEntry();
        app.run();
    }
}
