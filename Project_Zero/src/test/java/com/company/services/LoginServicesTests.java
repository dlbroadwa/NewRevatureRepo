package com.company.services;

import com.company.loginAccounts.LoginAccount;
import org.junit.Assert;
import org.junit.Test;

public class LoginServicesTests {
    @Test
    public void validateEnteredCredentialsNullTest() {
        LoginAccount credentialsToBeValidated = null;
        Assert.assertEquals(true, LoginServices.validateEnteredCredentialsAreNotNullOrEmpty(credentialsToBeValidated));
    }

    @Test
    public void validateEnteredCredentialsUserNameEmptyTest() {
        LoginAccount credentialsToBeValidated = new LoginAccount("", "342", false);
        Assert.assertEquals(true, LoginServices.validateEnteredCredentialsAreNotNullOrEmpty(credentialsToBeValidated));
    }

    @Test
    public void validateEnteredCredentialsPINEmptyTest() {
        LoginAccount credentialsToBeValidated = new LoginAccount("username", "", false);
        Assert.assertEquals(true, LoginServices.validateEnteredCredentialsAreNotNullOrEmpty(credentialsToBeValidated));
    }

    @Test
    public void verifyLoginTest() {

    }
}
