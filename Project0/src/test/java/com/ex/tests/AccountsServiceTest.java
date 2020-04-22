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

import main.java.com.ex.data.SQLDAO;
import main.java.com.ex.users.BankCustomerUser;
import main.java.com.ex.users.BankTellerUser;
import main.java.com.ex.users.User;
import main.java.com.services.AccountsService;

public class AccountsServiceTest {
	AccountsService as;
	User testTellerUser = new BankTellerUser("Joshua","Brewer","jbrewer","bo!ler2016",'t',451206);
	User testBankCustomer0 = new BankCustomerUser("Beth","Bryant","bbryant","ahdf2D!",'c',"7210 Springville Rd.","3129029873",12932.02);
	User testBankCustomer1 = new BankCustomerUser("Bethie","Bryant","bbryant22","ahdf2D!",'c',"7210 Springville Rd.","3129029873",1200.02);
	User updatedBankCustomer = new BankCustomerUser("Bethie","Bryant","bbryant202","ahdf2D!",'c',"7210 Hillsdale Dr.","2081529873",1200.02);
	User updatedTellerUser = new BankTellerUser("Joshua","Brewer","brewer35","pa$5Word",'t',120624);
	List<User> users = new ArrayList<>();
	
	@Mock
	SQLDAO dao;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
	    as = new AccountsService(dao);
	    users.add(testBankCustomer0);
	    users.add(testBankCustomer1);
	    users.add(testTellerUser);
	}
	
	@Test
	public void shouldReturnUpdatedCustomer() {
	    // ask the service for to update a customer
	    // assert that the customer way updated
		Mockito.when(dao.findById("bbryant202")).thenReturn(testBankCustomer1);
	    User actual = as.updateUserInfo(updatedBankCustomer, testBankCustomer1.getUsername());
	    Assert.assertEquals("Didn't return expected user", testBankCustomer1.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnUpdatedTeller() {
	    // ask the service for to update a teller
	    // assert that the teller way updated
		Mockito.when(dao.findById("brewer35")).thenReturn(testTellerUser);
	    User actual = as.updateUserInfo(updatedTellerUser, testTellerUser.getUsername());
	    Assert.assertEquals("Didn't return expected user", testTellerUser.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnUpdatedPasswordCustomer() {
	    // ask the service to update a customer's password
	    // assert that the password was changed
	    Mockito.when(dao.findById("bbryant")).thenReturn(testBankCustomer0);
	    User actual = as.updatePassword(testBankCustomer0, "nnsa!!M3");
	    Assert.assertEquals("Didn't return expected password", testBankCustomer0.getPassword(), actual.getPassword());
	}
	
	@Test
	public void shouldReturnUpdatedPasswordTeller() {
	    // ask the service to update a customer's password
	    // assert that the correct user was returned
	    Mockito.when(dao.findById("jbrewer")).thenReturn(testTellerUser);
	    User actual = as.updatePassword(testTellerUser, "passworD!2");
	    Assert.assertEquals("Didn't return expected user", testTellerUser.toString(), actual.toString());
	}
}
