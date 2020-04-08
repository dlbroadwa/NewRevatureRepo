package gameaccounts;
public class Account {
	private static int numAccounts=0;

	private String name;
	private	int balance;
		
	public Account(String name, int deposit){
		this.name = name;
		int balance = deposit;
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
}