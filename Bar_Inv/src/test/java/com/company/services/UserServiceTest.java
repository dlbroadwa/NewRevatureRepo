package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.data.UserRepository;
import com.company.DAO.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.w3c.dom.html.HTMLParagraphElement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class UserServiceTest {

    UserService service;
    List<User> users = new ArrayList();

    @Mock
    Repository<User,String, String> repo;
    @Mock
    UserService mockedService;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init(){
        service = new UserService(repo);
        User tmp = new User();
        User tmp2 = new User();
        User tmp3 = new User();
        tmp.setUserName("this dude");
        tmp.setPassword("1234");
        tmp2.setPassword("5678");
        tmp2.setUserName("some guy");
        tmp3.setUserName("bloke");
        tmp3.setPassword("9010");

        users.add(tmp);
        users.add(tmp2);
        users.add(tmp3);
    }

    @Test
    public void shouldGetAllUsers() throws SQLException {
        //ask for all the users
        //assert that all users are returned
        Mockito.when(repo.findAll()).thenReturn(users);
        List<User> actual = service.getAllUsers();
        Assert.assertArrayEquals(users.toArray(),actual.toArray());
    }


    @Test
    public void shouldVerifyUserAndPasswordMatchTrue(){
        //returns 1
        User tmp3 = new User();
        tmp3.setUserName("bloke");
        tmp3.setPassword("9010");

        Mockito.when(repo.findByID("bloke")).thenReturn(tmp3);
        int actual = service.userByName("bloke","9010");
        Assert.assertEquals(1,actual);

    }
   @Test
    public void shouldVerifyUserAndPasswordMatchFalse(){
        //returns a 0
       User tmp3 = new User();
       tmp3.setUserName("bloke");
       tmp3.setPassword("9010");

       Mockito.when(repo.findByID("bloke")).thenReturn(tmp3);
       int actual = service.userByName("bloke","9011");
       Assert.assertEquals(0,actual);

    }


    @Test
    public void shouldAddNewUser() {
        //ask the service to save a new user
        //assert that the entered information is added

//        Mockito.doNothing().
//                doThrow(new RuntimeException())
//                .when(mockedService).addUser("billy","addMe");
//
//        mockedService.addUser("billy","addMe");
//        User newUser = new User();
//        newUser.setPassword("addMe");
//        newUser.setUserName("billy");
//        Mockito.when(mockedService.addUser("billy","addMe")).thenReturn(1);
        int actual = service.addUser("billy","addMe");
        Assert.assertEquals(1,actual);

    }
}