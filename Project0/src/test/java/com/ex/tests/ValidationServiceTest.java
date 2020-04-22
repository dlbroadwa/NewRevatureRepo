package test.java.com.ex.tests;

import java.util.ArrayList;
import java.util.List;

import  org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import main.java.com.ex.data.DAO;
import main.java.com.ex.users.BankCustomerUser;
import main.java.com.ex.users.BankTellerUser;
import main.java.com.ex.users.User;
import main.java.com.services.ValidationService;

public class ValidationServiceTest {
	ValidationService vs;
	User testTellerUser = new BankTellerUser("Joshua","Brewer","jbrewer","bo!ler2016",'t',451206);
	User testBankCustomer0 = new BankCustomerUser("Beth","Bryant","bbryant","ahdf2D!",'c',"7210 Springville Rd.","3129029873",12932.02);
	User testBankCustomer1 = new BankCustomerUser("Bethie","Bryant","bbryant22","ahdf2D!",'c',"7210 Springville Rd.","3129029873",1200.02);
	List<User> users = new ArrayList<>();
	
	@Mock
	DAO<User,String> dao;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
	  vs = new ValidationService(dao);
	  users.add(testBankCustomer0);
	  users.add(testBankCustomer1);
	  users.add(testTellerUser);
	}
	
	@Test
	public void shouldReturnCorrectUserByUsername() {
	  // ask the service for to validate username
	  // assert that the correct user was returned
	  Mockito.when(dao.findById("bbryant")).thenReturn(testBankCustomer0);
	  User actual = vs.validate_username("bbryant");
	  Assert.assertEquals("Didn't return expected user", testBankCustomer0.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnNullByIncorrectUsername() {
	  // ask the service for to validate an invalid username
	  // assert that the a null value was returned
	  User actual = vs.validate_username("hhgfjb");
	  Assert.assertNull("Didn't return null", actual);
	}
	
	@Test
	public void shouldReturnCorrectUserByUsernameAndPassword() {
	  // ask the service for to validate username and password
	  // assert that the correct user was returned
	  Mockito.when(dao.findById("bbryant22")).thenReturn(testBankCustomer1);
	  User actual = vs.validate_username_password("bbryant22", "ahdf2D!");
	  Assert.assertEquals("Didn't return expected user", testBankCustomer1.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnNullByIncorrectPasswordCorrecUsername() {
	  // ask the service to validate an invalid password and a valid username
	  // assert that the a null value was returned
	  User actual = vs.validate_username_password("bbryant22","ahdf2aa");
	  Assert.assertNull("Didn't return null", actual);
	}
	
	@Test
	public void shouldReturnNullByIncorrectUsernameCorrectPassword() {
	  // ask the service to validate an valid password and a invalid username
	  // assert that the a null value was returned
	  User actual = vs.validate_username_password("hhgfjb","ahdf2D!");
	  Assert.assertNull("Didn't return null", actual);
	}
}
