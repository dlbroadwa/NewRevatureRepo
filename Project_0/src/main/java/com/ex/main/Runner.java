package com.ex.main;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Runner {
    protected String url;
    protected String username;
    protected String password;
    protected String defaultSchema;

    public abstract void run();
    public abstract Connection getConnection() throws SQLException;


    public String getDefaultSchema() {
        return this.defaultSchema;
    }

    String[] animals;


    public String[] getAnimals() {
        return animals;
    }

    public void setAnimals(String[] animals) {
        this.animals = animals;
    }

}
