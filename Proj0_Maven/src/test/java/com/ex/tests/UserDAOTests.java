package com.ex.tests;

import com.ex.dao.UserDAO;
import com.ex.io.UserSQLDatabase;
import com.ex.models.User;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTests {
    static DatabaseConnection dc;
    static UserDAO dao;

    @BeforeClass
    public static void init() {
        String url = "jdbc:postgresql://database-1.c7zjtw5vhjwr.us-east-2.rds.amazonaws.com:5432/postgres";
        String username = "library_admin";
        String password = "my$ecurep@ssw0rd";

        dc = new PostgreSQLConnection(url, username, password, "project0_tests");
        dao = new UserSQLDatabase(dc);

        Assert.assertTrue("Failed to initialize driver", dc.isDriverInitialized());
    }

    @Test
    public void shouldFindUser() {
        // Database already has this user
        User expected = new User(123450000, "John", "Doe");
        User actual = dao.getUserInfo(123450000);
        Assert.assertEquals("Didn't return correct user information!", expected, actual);
    }

    @Test
    public void shouldAddAndRemoveUser() {
        int newId = 55555555;
        User newUser = new User(newId, "Blahy", "Blahsome");
        boolean result = dao.add(newUser);
        Assert.assertTrue("add() didn't return true!", result);

        User actual = dao.getUserInfo(newId);
        Assert.assertEquals("Stored incorrect user info!", newUser, actual);

        result = dao.remove(newId);
        Assert.assertTrue("Couldn't remove user!", result);

        actual = dao.getUserInfo(newId);
        Assert.assertNull("User wasn't removed from the table!", actual);
    }

    @Test
    public void shouldUpdateUser() {
        User oldUser = dao.getUserInfo(123450002); // Bob Smith
        User newUser = new User(987654321, "Bobby", "Smithers");
        boolean result = dao.update(123450002, newUser);
        Assert.assertTrue("update() didn't return true!", result);

        User actual = dao.getUserInfo(987654321);
        Assert.assertEquals("Updated user info doesn't match expected data!", newUser, actual);

        // Clean up
        result = dao.update(987654321, oldUser);
        Assert.assertTrue("Couldn't restore old user data!", result);
    }

    @Test
    public void shouldFindDoes() {
        List<User> expected = new ArrayList<>();
        expected.add(new User(123450001, "Jane", "Doe"));
        expected.add(new User(123450000, "John", "Doe"));

        List<User> actual = dao.findUser("doe");
        Assert.assertArrayEquals("Didn't return expected users!", expected.toArray(), actual.toArray());
    }
}
