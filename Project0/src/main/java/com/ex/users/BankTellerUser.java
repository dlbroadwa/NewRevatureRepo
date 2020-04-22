package main.java.com.ex.users;

/**
 * BankTellerUser: a model of a teller at a bank that extends the User class
 * 
 * class variables
 * private int validationCode: the authorization code required before a teller can complete transactions on
 * a customer's account
 * 
 * @author jtb12_000
 *
 */

public class BankTellerUser extends User {
	
	private int validationCode;

	public BankTellerUser(){}
	
	public BankTellerUser (String first_name, String last_name, String username, String password, char access_level, int validationCode){
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.access_level = access_level;
		this.validationCode = validationCode;
	}
	
	/*
	 * Getters and Setters for non-inherited class variables
	 */

	public int getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(int validationCode) {
		this.validationCode = validationCode;
	}

	/*
	 * toString method for unit tests
	 */
	
	@Override
	public String toString() {
		return "BankTellerUser [validationCode=" + validationCode + ", first_name=" + first_name + ", last_name="
				+ last_name + ", username=" + username + ", password=" + password + ", access_level=" + access_level
				+ "]";
	}
	
	
}
