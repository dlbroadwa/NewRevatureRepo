package main.java.com.services;

import main.java.com.ex.data.DAO;
import main.java.com.ex.data.SQLDAO;
import main.java.com.ex.users.User;
import main.java.com.ex.users.BankCustomerUser;

/**
 * TransactionService: a service that updates deposit and withdraw transactions in the data repository and returns the new user
 * to the screen
 * 
 * class variables
 * private DAO<User, String> dao: the data repository access object that will allow for data persistance
 * 
 * methods
 * public User transact(char type, double amount, User user): a method that conducts withdraws or deposits and returns a new 
 * User if the transaction was valid and the old User if the transaction was invalid
 * 
 * @author jtb12_000
 *
 */

public class TransactionService {
	private DAO<User, String> dao;
	
	public TransactionService(DAO<User, String> dao) {
		this.dao = dao;
	}
	
	public User transact(char type, double amount, User user) {
		if(type == 'w' && ((BankCustomerUser)user).getBalance() - amount < 0){
			return user;
		} else if(type == 'w') {
			((SQLDAO)dao).updateBalance(user.getUsername(), ((BankCustomerUser)user).getBalance() - amount);
			return dao.findById(user.getUsername());
		} else {
			((SQLDAO)dao).updateBalance(user.getUsername(), ((BankCustomerUser)user).getBalance() + amount);
			return dao.findById(user.getUsername());
		}
	}
}
