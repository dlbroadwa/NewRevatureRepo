package com.company.services;

import com.company.DAO.LoginAccountDAO;
import com.company.loginAccounts.LoginAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class LoginServicesTests {

    @Mock
    private LoginAccountDAO loginAccountDAO = null;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

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
    public void verifyLoginWithNoMatchingUsernameTest() {
        LoginAccount enteredCredentials = new LoginAccount("John1234", "12345", false);

        LoginAccount[] retrievedAccountsByUserName = new LoginAccount[0];
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(retrievedAccountsByUserName);

        Assert.assertEquals(false, LoginServices.verifyLoginAccount(enteredCredentials, loginAccountDAO));
    }

    @Test
    public void verifyLoginWithMatchingUsernamePinAdminTest() {
        LoginAccount enteredCredentials = new LoginAccount("John1234", "12345", false);

        LoginAccount[] retrievedAccountsByUserName = { new LoginAccount(enteredCredentials.getUserName(), enteredCredentials.getPin(), enteredCredentials.isAdmin())};
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(retrievedAccountsByUserName);

        Assert.assertEquals(true, LoginServices.verifyLoginAccount(enteredCredentials, loginAccountDAO));
    }

    @Test
    public void verifyLoginWithMatchingUsernameAndAdminAndMismatchingPasswordTest() {
        LoginAccount enteredCredentials = new LoginAccount("John1234", "12345", false);

        LoginAccount[] retrievedAccountsByUserName = { new LoginAccount(enteredCredentials.getUserName(), "GoAway!", enteredCredentials.isAdmin())};
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(retrievedAccountsByUserName);

        Assert.assertEquals(false, LoginServices.verifyLoginAccount(enteredCredentials, loginAccountDAO));
    }
}
