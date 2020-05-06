package com.ex.tests;

import com.ex.data.ProductTypeHandler;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class ProdTypeHandlerTest {
    private static DatabaseConnection dc;
    private static ProductTypeHandler pth;

    @BeforeClass
    public static void init() {
        String url = "jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres";
        String username = "postgres";
        String password = "revature";

        dc = new PostgreSQLConnection(url, username, password, "project1_tests");
        Assert.assertTrue("Failed to initialize DB driver", dc.isDriverInitialized());

        pth = new ProductTypeHandler(dc);
    }

    @Test
    public void shouldGetID() throws SQLException {
        int id = pth.typeToID("Dog Food");
        Assert.assertEquals("Didn't return ID for Dog Food!", 0, id);
    }

    @Test
    public void shouldGetType() throws SQLException {
        String type = pth.idToType(1);
        Assert.assertEquals("Didn't return type for ID 1!", "Cat Food", type);
    }
}
