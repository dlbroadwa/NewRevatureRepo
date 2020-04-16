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

public class UserServiceTest {

    UserService service;
    List<User> users = new ArrayList();

    @Mock
    Repository<User,String> repo;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init(){
        service = new UserService(repo);
        User tmp = new User();
        tmp.setUserName("this dude");
        tmp.setPassword("1234");
        users.add(tmp);
    }
    @Test
    public void shouldGetAllUsers() throws SQLException {
        //ask for all the users
        //assert that all users are returned
        Mockito.when(repo.findAll()).thenReturn(users);
        List<User> actual = service.getAllUsers();
        Assert.assertArrayEquals(users.toArray(),actual.toArray());
    }

    @Before
    public void startOneUser(){
        service = new UserService(repo);
        User tmp = new User();
        tmp.setUserName("this dude");
        tmp.setPassword("1234");
    }
    @Test
    public void shouldReturnOneUser() {
        Mockito.when(repo.findByID("this dude")).thenReturn(tmp);

    }

    @Test
    public void shouldVerifyUserAndPasswordMatch(){

    }

    @Before
    public void initAddUser(){
        service = new UserService(repo);

    }
    @Test
    public void shouldAddNewUser() {
        //ask the service to save a new user
        //assert that the entered information is added

        User tmp = new User();
        tmp.setPassword("addMe");
        tmp.setUserName("billy");
        repo.save(tmp);


    }
//these work since they aren't used
    @Test
    public void removeUser() {
    }

    @Test
    public void updateUser() {
    }
}