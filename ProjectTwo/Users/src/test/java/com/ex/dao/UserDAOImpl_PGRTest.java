package com.ex.dao;

import com.ex.model.Address;
import com.ex.model.PhoneCarrier;
import com.ex.model.User;
import com.ex.services.UserService;
import junit.framework.TestCase;

public class UserDAOImpl_PGRTest extends TestCase {

    public void testAddUser() {
        UserService userService = new UserService();

        Address address = new Address(13, "Delores", "apt 5", "Truckee", "CA", "USA", 66666);
        User user = new User("John", "Sheerin", "7752305812",
                new PhoneCarrier(), "john@mail.com", "password", address, 13, true);

        userService.addUser(user);


        }
}