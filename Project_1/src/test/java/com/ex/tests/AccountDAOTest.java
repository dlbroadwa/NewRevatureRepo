package com.ex.tests;

import com.ex.data.AccountSQLDatabase;
import com.ex.data.GenericDAO;
import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountDAOTest extends DAOTest<Account, String> {
    //static private GenericDAO<Account, String> dao;

    @Before
    public void init() {
        dao = new AccountSQLDatabase(dc);

        Assert.assertTrue("Failed to initialize DB driver", dc.isDriverInitialized());
    }

    @Test
    public void shouldFindAccount() {
        // Database already contains this record
        Account expected = new Account("Perry", "abc@123.com", "correct horse battery staple", false, false);

        super.shouldFindObject(expected, "abc@123.com");
    }

    @Test
    public void shouldFindNoAccount() {
        super.shouldFindNoObject("ajkldsfjaskl");
    }

    @Test
    public void shouldAddAndRemoveAccount() {
        String email = "qwertyuiop@asdfghjkl.co.uk";
        Account newUser = new Account("foo bar", email, "password", true, false);

        super.shouldAddAndRemoveObject(newUser, email);
    }

    @Test
    public void shouldUpdateAccount() {
        String oldEmail = "bob@doe";
        String newEmail = "jsmith@example.com";
        Account newInfo = new Account("John Smith", newEmail, "asdf", true, true);

        super.shouldUpdateObject(newInfo, oldEmail, newEmail);
    }

    @Test
    @Override
    public void shouldFindAllObjects() {
        List<Account> expected = new ArrayList<>();
        expected.add(new Account("Bob Doe", "bob@doe", "admin", false, false));
        expected.add(new Account("Perry", "abc@123.com", "correct horse battery staple", false, false));
        expected.add(new Account("Spider-Man", "(redacted)@(top secret)", "password", false, false));

        List<Account> actual = dao.findAll();
        Collections.sort(actual, (a, b) -> a.getName().compareTo(b.getName()));

        Assert.assertArrayEquals("Didn't return correct list of Accounts!", expected.toArray(), actual.toArray());
    }
}
