package com.ex.dao;

import com.ex.model.Address;
import com.ex.model.PhoneCarrier;
import com.ex.model.User;
import com.ex.services.UserService;
import junit.framework.TestCase;

public class UserDAOImpl_PGRTest extends TestCase {

    Address address = new Address(1234, "Memory Lane", "null", "Grand Rapids", "MI",
            "USA", 49341);
    User user = new User("John", "Sheerin", "7756666666", new PhoneCarrier(),
            "john@mail.com", "password", address, 0, false);


    public void testAddUser() {
        UserService userService = new UserService();


//        //PhoneCarrier carrier = new PhoneCarrier();
//        Address address = new Address(1234, "Memory Lane", "null", "Grand Rapids", "MI",
//                "USA", 49341);
//        User user = new User("John", "Sheerin", "7756666666", new PhoneCarrier(),
//                "john@mail.com", "password", address, 0, true);


        userService.addUser(user);


        }

    public void testDisableUser() {
        UserService userService = new UserService();

        userService.disableUser(user, false);

    }
}