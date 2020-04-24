package com.company.DAO;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DatabaseConnectionTest {

    //test connection to the database
    @Test
    public void connect() {
        DatabaseConnection connTest = new DatabaseConnection();
        connTest.connect();
        Assert.assertNotNull(connTest);
    }
}