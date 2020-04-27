package com.williamchung.project0.tests;

import com.williamchung.project0.models.User;
import com.williamchung.project0.repositories.UserRepository;
import com.williamchung.project0.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UserServiceTest {
    UserService userService;
    User testUser1;
    User testUser2;

    @Mock
    UserRepository repository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        this.userService = new UserService(repository);
        testUser1 = new User("testUser1", "testPassword1");
        testUser1.setId(1);
        testUser2 = new User("testUser2", "testPassword2");
        testUser2.setId(2);
    }

    @Test
    public void shouldConstructUserService(){
        Assert.assertNotNull(userService);
    }

    @Test
    public void shouldAuthenticateUser(){
        Mockito.when(repository.findByUsername("testUser1")).thenReturn(testUser1);
        User result = userService.authenticateUser("testUser1", "testPassword1");
        Assert.assertEquals("User should be authenticated", testUser1, result);
    }

    @Test
    public void shouldNotAuthenticateUser(){
        Mockito.when(repository.findByUsername("testUser2")).thenReturn(testUser2);
        User result = userService.authenticateUser("testUser2", "wrongPassword");
        Assert.assertNull("User should be not authenticated", result);
    }

    @Test
    public void shouldNotValidateDeposit(){
        boolean result = userService.depositValid("-1");
        Assert.assertFalse("Deposit amount should be not be valid", result);
    }

    @Test
    public void shouldReturnFalseNotThrowException(){
        boolean result = userService.depositValid("not a number");
        Assert.assertFalse("exception should be caught and return false", result);
    }

    @Test
    public void shouldDeposit(){
        userService.deposit(testUser1, "100.00");
        double actualBalance = testUser1.getCheckingBalance();
        double expectedBalance = 100.00;
        Assert.assertEquals("Balance after deposit should be 100.00", expectedBalance, actualBalance, 0.0f);
    }

    @Test
    public void shouldNotValidateWithdrawal(){
        testUser1.setCheckingBalance(99.99);
        boolean result = userService.withdrawalValid("100.00", testUser1);
        Assert.assertFalse("balance insufficient, should be invalid", result);
    }

    @Test
    public void shouldWithdraw(){
        testUser1.setCheckingBalance(15.00);
        userService.withdraw(testUser1, "15.00");
        Assert.assertEquals("Balance after withdrawal should be 0.00", 0.00, testUser1.getCheckingBalance(), 0.0f);
    }

    @Test
    public void shouldNotValidateTransfer(){
        Mockito.when(repository.findByUsername("testUser3")).thenReturn(null);
        boolean result = userService.transferValid("0.00", testUser1, "testUser3");
        Assert.assertFalse("testUser3 doesnt exist and shouldn't be valid", result);
    }

    @Test
    public void shouldValidateTransfer(){
        Mockito.when(repository.findByUsername("testUser2")).thenReturn(testUser2);
        boolean result = userService.transferValid("0.00", testUser1, "testUser2");
        Assert.assertTrue("amount is 0, testUser1 and testUser2 both exist. should be valid", result);
    }

    @Test
    public void shouldTransfer(){
        Mockito.when(repository.findByUsername("testUser2")).thenReturn(testUser2);
        Mockito.doNothing().when(repository).update(testUser1, 1);
        Mockito.doNothing().when(repository).update(testUser2, 2);

        testUser1.setCheckingBalance(15.00);
        testUser2.setCheckingBalance(0.00);

        userService.transfer(testUser1, "10.00", "testUser2");

        Assert.assertEquals("User1 bal should be 5.00", 5.00, testUser1.getCheckingBalance(), 0.0f);
        Assert.assertEquals("User2 bal should be 10.00", 10.00, testUser2.getCheckingBalance(), 0.0f);
    }
}
