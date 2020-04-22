package main.java.com.ex.app;

import java.util.Scanner;


import main.java.com.ex.connections.PostgresConnection;
import main.java.com.ex.data.DAO;
import main.java.com.ex.data.SQLDAO;
import main.java.com.ex.screens.Screen;
import main.java.com.ex.screens.WelcomeScreen;
import main.java.com.ex.users.User;
import main.java.com.services.AccountsService;
import main.java.com.services.TransactionService;
import main.java.com.services.ValidationService;

/**
 * OnlineBankApplication: the banking application
 * 
 * class variables
 * private Scanner scanner: single scanner that will be passed to all screens
 * private Screen screen: screen object that run will use to switch between screens
 * private DAO<User, String> dao: data access object that will be use for data persistence
 * private PostgresConnection pg_connection: postgres connection object used for 
 * private ValidationService vs: service that validates usernames and passwords for the screens
 * private TransactionService ts: service that completes deposit and withdraw transactions for the screens
 * private AccountsService as: service that manipulates the data in user accounts for the screens
 * 
 * methods
 * run(): a method that displays screens to the user
 * 
 * @author jtb12_000
 *
 */
public class OnlineBankApplication extends Application {
	
	private Scanner scanner;
	private Screen screen;
	private DAO<User, String> dao;
	private PostgresConnection pg_connection; 
	private ValidationService vs;
	private TransactionService ts;
	private AccountsService as;
	
	public OnlineBankApplication() {
		scanner = new Scanner(System.in);
		screen = new WelcomeScreen();
		pg_connection = new PostgresConnection(System.getenv("sql_url"), System.getenv("sql_username"), System.getenv("sql_password"));
		dao = new SQLDAO(pg_connection);
		vs = new ValidationService(dao);
		ts = new TransactionService(dao);
		as = new AccountsService(dao);
	}

	@Override
	public void run() {
		while(screen != null) {
			screen = screen.display(this);
		}
	}
	
	/*
	 * Getters for all the objects used by the screens or services.
	 */

	public Scanner getScanner() {
		return scanner;
	}	
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public DAO<User,String> getDAO() {
		return dao;
	}
	
	public ValidationService getValidationService() {
		return vs;
	}
	
	public TransactionService getTransactionService() {
		return ts;
	}
	
	public AccountsService getAccountsService() {
		return as;
	}

}
