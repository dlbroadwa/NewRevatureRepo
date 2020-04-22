package main.java.com.ex.users;

/**
 * BankCustomerUser: a model of a customer account at a bank that extends a User class
 * 
 * class variables:
 * private double balance: the amount of money in a customer's account
 * private String address: the address where the customer lives
 * private String phone_number: the customer's phone number
 * 
 * @author jtb12_000
 *
 */

public class BankCustomerUser extends User {
	
	private double balance;
	private String address;
	private String phone_number;
	
	public BankCustomerUser(){}
	
	public BankCustomerUser (String first_name, String last_name, String username, String password, char access_level, String address, String phone_number, double balance) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.access_level = access_level;
		this.address = address;
		this.phone_number = phone_number;
		this.balance = balance;
	}
	
	/*
	 * Getter and Setter Methods for all non-inherited class variables
	 */

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	/*
	 * toString method for unit tests
	 */
	
	@Override
	public String toString() {
		return "BankCustomerUser [balance=" + balance + ", address=" + address + ", phone_number=" + phone_number
				+ ", first_name=" + first_name + ", last_name=" + last_name + ", username=" + username + ", password="
				+ password + ", access_level=" + access_level + "]";
	}
	
	
}
