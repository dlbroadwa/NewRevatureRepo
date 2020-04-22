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
import org.postgresql.util.PSQLException;

/***
 * Contains unit tests for the LoginServices class.
 */
public class LoginServicesTests {
    private static LoginAccount enteredCredentials = null;

    @Mock
    private LoginAccountDAO loginAccountDAO = null;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        enteredCredentials = new LoginAccount("John1234", "12345", false);
    }

    /***
     * This unit test for the validateEnteredCredentials() method checks to make sure that the method returns true when passed a null value as a parameter.
     */
    @Test
    public void validateEnteredCredentialsNullTest() {
        Assert.assertEquals(true, LoginServices.validateEnteredCredentialsAreNullOrEmpty(null));
    }

    /***
     * This unit test for the validateEnteredCredentials() method checks to make sure that the method returns true when the username of the credentials is an empty string.
     */
    @Test
    public void validateEnteredCredentialsUserNameEmptyTest() {
        LoginAccount credentialsToBeValidated = new LoginAccount("", "342", false);
        Assert.assertEquals(true, LoginServices.validateEnteredCredentialsAreNullOrEmpty(credentialsToBeValidated));
    }

    /***
     * This unit test for the validateEnteredCredentials() method checks to make sure that the method returns true when the username of the credentials is nothing but whitespace.
     */
    @Test
    public void validateEnteredCredentialsUserNameHasWhiteSpaceTest() {
        LoginAccount credentialsToBeValidated = new LoginAccount("   ", "342", false);
        Assert.assertEquals(true, LoginServices.validateEnteredCredentialsAreNullOrEmpty(credentialsToBeValidated));
    }

    /***
     * This unit test for the validateEnteredCredentials() method checks to make sure that the method returns true when the pin of the credentials is an empty string.
     */
    @Test
    public void validateEnteredCredentialsPINEmptyTest() {
        LoginAccount credentialsToBeValidated = new LoginAccount("username", "", false);
        Assert.assertEquals(true, LoginServices.validateEnteredCredentialsAreNullOrEmpty(credentialsToBeValidated));
    }

    /***
     * This unit test for the validateEnteredCredentials() method checks to make sure that the method returns true when the pin of the credentials is nothing but whitespace.
     */
    @Test
    public void validateEnteredCredentialsPINHasWhiteSpaceTest() {
        LoginAccount credentialsToBeValidated = new LoginAccount("username", "   ", false);
        Assert.assertEquals(true, LoginServices.validateEnteredCredentialsAreNullOrEmpty(credentialsToBeValidated));
    }

    @Test
    public void verifyLoginWithNoMatchingUsernameTest() throws PSQLException {

        LoginAccount[] retrievedAccountsByUserName = new LoginAccount[0];
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(retrievedAccountsByUserName);

        Assert.assertEquals(false, LoginServices.verifyLoginAccount(enteredCredentials, loginAccountDAO));
    }

    @Test
    public void verifyLoginWithMatchingUsernamePinAdminTest() throws PSQLException {

        LoginAccount[] retrievedAccountsByUserName = { new LoginAccount(enteredCredentials.getUserName(), enteredCredentials.getPin(), enteredCredentials.isAdmin())};
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(retrievedAccountsByUserName);

        Assert.assertEquals(true, LoginServices.verifyLoginAccount(enteredCredentials, loginAccountDAO));
    }

    @Test
    public void verifyLoginWithMatchingUsernameAndAdminAndMismatchingPasswordTest() throws PSQLException {

        LoginAccount[] retrievedAccountsByUserName = { new LoginAccount(enteredCredentials.getUserName(), "GoAway!", enteredCredentials.isAdmin())};
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(retrievedAccountsByUserName);

        Assert.assertEquals(false, LoginServices.verifyLoginAccount(enteredCredentials, loginAccountDAO));
    }

    @Test
    public void customerExistsNullTest() {
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(null);
        Assert.assertEquals(false, LoginServices.customerExists(enteredCredentials.getUserName(), loginAccountDAO));
    }

    @Test
    public void customerExistsReturnsZeroLengthArrayTest() {
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(new LoginAccount[0]);
        Assert.assertEquals(false, LoginServices.customerExists(enteredCredentials.getUserName(), loginAccountDAO));
    }

    @Test
    public void customerExistsReturnsLoginAccountTest() {
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(new LoginAccount[]{enteredCredentials});
        Assert.assertEquals(true, LoginServices.customerExists(enteredCredentials.getUserName(), loginAccountDAO));
    }

    @Test
    public void customerExistsReturnsAdminLoginAccountTest() {
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(new LoginAccount[]{new LoginAccount(enteredCredentials.getUserName(), enteredCredentials.getPin(), true)});
        Assert.assertEquals(false, LoginServices.customerExists(enteredCredentials.getUserName(), loginAccountDAO));
    }

    @Test
    public void loginAccountExistsNullTest() {
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(null);
        Assert.assertEquals(false, LoginServices.loginAccountExists(enteredCredentials.getUserName(), loginAccountDAO));
    }

    @Test
    public void loginAccountExistsReturnsZeroLengthArrayTest() {
        Mockito.when(loginAccountDAO.retrieveByID(enteredCredentials.getUserName())).thenReturn(new LoginAccount[0]);
        Assert.assertEquals(false, LoginServices.loginAccountExists(enteredCredentials.getUserName(), loginAccountDAO));
    }
}
