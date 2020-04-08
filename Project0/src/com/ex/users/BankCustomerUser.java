package com.ex.users;

public class BankCustomerUser extends User {
	
	private int balance;
	
	BankCustomerUser(){}
	
	BankCustomerUser (String fullname, String username, String password, String accessLevel, int balance){
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;
		this.balance = balance;
	}
	
	@Override
	public void transact() {
		// TODO Auto-generated method stub
		
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
