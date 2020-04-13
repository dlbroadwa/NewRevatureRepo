package gameaccounts;

import java.io.*;
import java.util.ArrayList;

public class Account {
	private String name;
	private String password;
	private	int balance;
	private boolean isAdmin;
	private ArrayList<Messages> messages;

	public Account(String name, String password){
		this.name = name;
		this.password = password;
		this.isAdmin=false;
		messages=new ArrayList<>(16);
	}
		
	public Account(String name, String password, boolean isAdmin) {
		this.name = name;
		this.password = password;
		this.isAdmin=isAdmin;
		messages=new ArrayList<>(16);
		if (isAdmin){
			//create a messaging txt file
			//only admin messages are backed up
			File file = new File("Resources/"+name+"Messages.txt");
			try {
				if (!file.createNewFile()) {
					System.out.println("Messages already exist for account");
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	public Account(String name, String password, boolean isAdmin, int deposit){
		this.name = name;
		this.password = password;
		this.balance = deposit;
		messages= new ArrayList<>(16);
		this.isAdmin=isAdmin;
		if(isAdmin){
			populateMessages();
		}
	}

	private void populateMessages(){
		try{
			FileReader reader = new FileReader("Resources/" + name + "Messages.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			String[] sline;
			//line split into 2 strings
			while ((line = bufferedReader.readLine()) != null) {
				sline=line.split(";");
				messages.add(new Messages(sline[0],sline[1]));
			}
			reader.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void receive(String line, String name){
		messages.add(new Messages(line,name));
		if(isAdmin) {
			updateMessages();
		}
	}

	public void updateMessages(){
		try {
			FileWriter writer = new FileWriter("Resources/" + name + "messages.txt");
			for (Messages i:messages) {
				writer.append(i.message).append(";").append(i.name).append("\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteMessage(int index){
		messages.remove(index);
		if(isAdmin) {
			updateMessages();
		}
	}

	public void spend(int request) {
		
		if(balance>=request) {
			balance -= request;
		}
		else {
			System.out.println("You do not have enough credits.");
		}
	}
	public void insert(int deposit) {
		balance += deposit;
	}
	
	public String getName() {
		return name;
	}
	public int getBalance() {
		return balance;
	}
	public String getPassword(){ return password; }
	public boolean getIsAdmin(){ return isAdmin; }

	public void viewAll() {
		for (Messages i:messages) {
			System.out.println(i.message+"\tlove "+i.name);
		}
	}
}
