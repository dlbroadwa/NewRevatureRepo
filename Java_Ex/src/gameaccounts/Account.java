package gameaccounts;

import java.io.*;
import java.util.ArrayList;

public class Account {
	private static int numAccounts=0;

	private String name;
	private String password;
	private	int balance;
	private boolean isAdmin;
	private ArrayList<String> messages;
	private FileReader reader;
	private FileWriter writer;

	public Account(String name, String password){
		this.name = name;
		this.password = password;
		this.isAdmin=false;
		messages=new ArrayList<String>(16);
		numAccounts++;
	}
		
	public Account(String name, String password, boolean isAdmin){
		this.name = name;
		this.password = password;
		this.isAdmin=isAdmin;
		messages=new ArrayList<String>(16);
		numAccounts++;
		if (isAdmin){
			//create a messaging txt file
			//only admin messages are backed up
			File file = new File("Resources/"+name+"Messages");
			try {
				file.createNewFile();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	public Account(String name, String password, boolean isAdmin, int deposit){
		this.name = name;
		this.password = password;
		this.balance = deposit;
		messages=new ArrayList<String>(16);
		this.isAdmin=isAdmin;
		if(isAdmin){
			populateMessages();
		}
		numAccounts++;

	}

	private void populateMessages(){
		try{
			reader = new FileReader("Resources/"+name+"Messages.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			//line split into four strings
			while ((line = bufferedReader.readLine()) != null) {
				messages.add(line);
			}
			reader.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return;
	}

	public void receive(String line){
		messages.add(line);
		if(isAdmin) {
			try {
				writer = new FileWriter("Resources/" + name + "messages.txt");
				writer.append(line);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	public void deleteMessage(int index){
		String line="";
		messages.remove(index);
		for(int i=0; i<messages.size(); i++){
			line+=messages.get(i)+"\n";
		}
		if(isAdmin) {
			try {
				writer = new FileWriter("Resources/" + name + "messages.txt");
				writer.write(line);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
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

	public void delete() {
		numAccounts--;
		return;
	}
	
	public String getName() {
		return name;
	}
	public int getBalance() {
		return balance;
	}
	public String getPassword(){ return password; }
	public boolean getIsAdmin(){ return isAdmin; }
}
