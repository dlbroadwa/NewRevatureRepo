package com.ex.services;

import com.ex.dao.UserDAO;
import com.ex.dao.UserDAOImpl_PGR;
import com.ex.model.Address;
import com.ex.model.PhoneCarrier;
import com.ex.model.User;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UserServiceTest extends TestCase {

    Address address = new Address(1234, "Memory Lane", "null", "Grand Rapids", "MI",
            "USA", 49341);
    User user = new User("John", "Sheerin", "7756666666", new PhoneCarrier(),
            "john@mail.com", "password", address, 0, false);


    @Mock
    UserDAO mockDao;

    @InjectMocks
    UserService service;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        service = new UserService(mockDao);
    }


    @Test
    public void testLoginUser() {
        if (user.isInactiveUser() == false) {
            System.out.println("Active User!");
            try {
                assertEquals("john@mail.com", user.getEmail() );
                assertEquals("password", user.getPassword() );
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User has been disabled");
        }
    }
}