package com.ex.dao;

import com.ex.model.Address;
import com.ex.model.PhoneCarrier;
import com.ex.model.User;
import com.ex.services.UserService;
import junit.framework.TestCase;

public class UserDAOImpl_PGRTest extends TestCase {

    public void testAddUser() {
        UserService userService = new UserService();

        PhoneCarrier carrier = new PhoneCarrier();
        Address address = new Address(1234, "Memory Lane", "null", "Grand Rapids", "MI",
                "USA", 49341);
        User user = new User("John", "Sheerin", "7752305812", carrier,
                "john@mail.com", "password", address, 0, true);

        userService.addUser(user);


        }
}