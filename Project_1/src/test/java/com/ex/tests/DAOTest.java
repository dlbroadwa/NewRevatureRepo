package com.ex.tests;

import com.ex.data.GenericDAO;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import org.junit.Assert;

import java.util.List;

public abstract class DAOTest<T, ID> {
    protected static DatabaseConnection dc;
    protected GenericDAO<T, ID> dao = null;

    public DAOTest() {
        String url = "jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres";
        String username = "postgres";
        String password = "revature";

        dc = new PostgreSQLConnection(url, username, password, "project1_tests");
        Assert.assertTrue("Failed to initialize DB driver", dc.isDriverInitialized());
    }

    public void shouldFindObject(T obj, ID id) {
        if (dao == null) return;
        T actual = dao.findByID(id);

        Assert.assertEquals("Didn't find correct object", obj, actual);
    }

    public void shouldFindNoObject(ID id) {
        if (dao == null) return;
        T actual = dao.findByID(id);

        Assert.assertNull(actual);
    }

    public void shouldAddAndRemoveObject(T obj, ID id) {
        if (dao == null) return;
        boolean result = dao.add(obj);
        Assert.assertTrue("Couldn't add new object!", result);

        T actual = dao.findByID(id);
        Assert.assertEquals("Added object has incorrect info!", obj, actual);

        result = dao.remove(id);
        Assert.assertTrue("Failed to remove object!", result);

        // Make sure it's gone
        actual = dao.findByID(id);
        Assert.assertNull("Didn't actually remove object!", actual);
    }

    public void shouldUpdateObject(T newInfo, ID prevId, ID newId) {
        if (dao == null) return;
        T oldInfo = dao.findByID(prevId);
        boolean result = dao.update(prevId, newInfo);
        Assert.assertTrue("Failed to update object!", result);

        // Check that object was updated correctly
        T actual = dao.findByID(newId);
        Assert.assertEquals("Updated object has incorrect data!", newInfo, actual);

        // Clean up
        result = dao.update(newId, oldInfo);
        Assert.assertTrue("Failed to restore old object info!", result);
    }

    public abstract void shouldFindAllObjects();
}
