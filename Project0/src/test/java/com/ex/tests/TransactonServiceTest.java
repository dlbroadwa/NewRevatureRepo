package test.java.com.ex.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.junit.Rule;

import main.java.com.ex.data.SQLDAO;
import main.java.com.ex.users.BankCustomerUser;
import main.java.com.ex.users.BankTellerUser;
import main.java.com.ex.users.User;
import main.java.com.services.TransactionService;

public class TransactonServiceTest {
	TransactionService ts;
	User testTellerUser = new BankTellerUser("Joshua","Brewer","jbrewer","bo!ler2016",'t',451206);
	User testBankCustomer0 = new BankCustomerUser("Beth","Bryant","bbryant","ahdf2D!",'c',"7210 Springville Rd.","3129029873",12932.02);
	User testBankCustomer1 = new BankCustomerUser("Bethie","Bryant","bbryant22","ahdf2D!",'c',"7210 Springville Rd.","3129029873",1200.02);
	List<User> users = new ArrayList<>();
	
	@Mock
	SQLDAO dao;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
	  ts = new TransactionService(dao);
	  users.add(testBankCustomer0);
	  users.add(testBankCustomer1);
	  users.add(testTellerUser);
	}
	
	@Test
	public void shouldReturnWitdrawUpdatedUserWithdraw() {
	  // ask the service for to withdraw
	  // assert that the withdraw was completed
	  Mockito.when(dao.findById("bbryant22")).thenReturn(testBankCustomer1);
	  User actual = ts.transact('w', 200.00, testBankCustomer1);
	  Assert.assertEquals(((BankCustomerUser)testBankCustomer1).getBalance(), ((BankCustomerUser)actual).getBalance(), 0.0);
	}
	
	@Test
	public void shouldReturnWitdrawUpdatedUserDeposit() {
	  // ask the service for to deposit
	  // assert that the deposit was completed
	  Mockito.when(dao.findById("bbryant")).thenReturn(testBankCustomer0);
	  User actual = ts.transact('d', 20000.00, testBankCustomer0);
	  Assert.assertEquals(((BankCustomerUser)testBankCustomer0).getBalance(), ((BankCustomerUser)actual).getBalance(), 0.0);
	}
	
	@Test
	public void shouldReturnWitdrawOlddUser() {
	  // ask the service for to overdraw account
	  // assert that the overdraw was not completed and old account was returned
	  User actual = ts.transact('w', 2000.00, testBankCustomer1);
	  Assert.assertEquals("Didn't return expected user", actual, testBankCustomer1);
	}
	
	
}
