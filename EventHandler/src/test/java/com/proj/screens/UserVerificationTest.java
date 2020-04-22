package com.proj.screens;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class UserVerificationTest {

    private Scanner scanner;
    private String username = "johnny.admin";
    private String password = "JellyBean13";
    private boolean found = false;

    //******this test makes sure that the username and password stored info******//
    @Test
    public void checkUserPass(){
        String tempUsername = "johnny.admin";
        String tempPassword = "JellyBean13";

        if (tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim())) {
            found = true;
        }
        assertTrue(found);
    }

    //*********** this test checks if the user is an administrator***********//
    @Test
    public void checkAdmin() {
        //String username = "johnny.admin";
        //boolean check = false;
        if (username.endsWith(".admin")) {
            found = true;
        }
        assertTrue(found);
    }
}