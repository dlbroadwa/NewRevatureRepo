package gameaccounts;
public class Account {
	private static int numAccounts;
	private String name;
	private	int balance;
		
	public Account(String name, int deposit){
		this.name = name;
		int balance = deposit;
	}
	public int spend(int request) {
		
		if(balance>=request) {
			balance -= request;
		}
		else {
			System.out.println("You do not have enough credits.");
			return 0;
		}
		return 1;
	}
	public int insert(int deposit) {
		balance += deposit;
		return 1;
	}
	
	public String getName() {
		return name;
	}
	public int getBalance() {
		return balance;
	}
}
