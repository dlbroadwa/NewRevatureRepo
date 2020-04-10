package gameaccounts;
public class Account {
	private static int numAccounts=0;

	private String name;
	private String password;
	private	int balance;
	private boolean isAdmin;

	public Account(String name, String password){
		this.name = name;
		this.password = password;
		this.isAdmin=false;
		numAccounts++;
		isAdmin=false;
	}
		
	public Account(String name, String password, boolean isAdmin){
		this.name = name;
		this.password = password;
		this.isAdmin=isAdmin;
		numAccounts++;
		isAdmin=false;
	}

	public Account(String name, String password, boolean isAdmin, int deposit){
		this.name = name;
		this.password = password;
		this.balance = deposit;
		this.isAdmin=isAdmin;
		numAccounts++;

	}

	public Account(String name){
		this.name = name;
		numAccounts++;

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

	public int delete(int deposit) {
		numAccounts--;
		return 1;
	}
	
	public String getName() {
		return name;
	}
	public int getBalance() {
		return balance;
	}
	public String getPassword(){ return password; }
}
