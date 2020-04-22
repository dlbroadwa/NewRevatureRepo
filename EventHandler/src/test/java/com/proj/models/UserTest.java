package com.proj.models;

import org.junit.Test;
import org.mockito.Mock;

import java.util.Scanner;

import static org.junit.Assert.*;

public class UserTest {
    Scanner scanner;
    private String username = "Bill";
    private String password = "Password";
    private int id = 0;

    @Mock
    User userTest;

    //**********test if the username can be retrieved**************//
    @Test
    public void getNewUsername() {
        this.userTest = new User();
        this.userTest.setNewUsername(this.username);
        assertEquals(username, this.userTest.getNewUsername());
    }

    //************checks if the username has been changed**************//
    @Test
    public void setNewUsername() {
        this.userTest = new User();
        this.userTest.setNewUsername("John");
        String name = this.userTest.getNewUsername();
        this.userTest.setNewUsername("John");
        assertEquals(name, this.userTest.getNewUsername());
    }

    //*************tests if the password is able to be retrieved**********//
    @Test
    public void getNewPassword() {
        this.userTest = new User();
        this.userTest.setNewPassword(this.username);
        assertEquals(username, this.userTest.getNewPassword());
    }

    //***********tests if user sets a password****************//
    @Test
    public void setNewPassword() {
        this.userTest = new User();
        this.userTest.setNewPassword("JellyBean13");
        String password = this.userTest.getNewPassword();
        this.userTest.setNewPassword("JellyBean13");
        assertEquals(password, this.userTest.getNewPassword());
    }
}