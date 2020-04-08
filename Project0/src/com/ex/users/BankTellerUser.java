package com.ex.users;

public class BankTellerUser extends User {
	
	private int validationCode;

	BankTellerUser(){}
	
	BankTellerUser (String fullname, String username, String password, String accessLevel, int validationCode){
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;
		this.validationCode = validationCode;
	}
	
	@Override
	public void transact() {
		// TODO Auto-generated method stub
		
	}

	public int getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(int validationCode) {
		this.validationCode = validationCode;
	}


}
