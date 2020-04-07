package BankAccount.app;

import BankAccount.DAO.FileIoDAO;

public class Main {

public static void main(String[] args) {
		
		FileIoDAO user = new FileIoDAO();
		
		user.userAuth();
		
	}
}
