package ticket.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ticket.model.User;

public class UserDAOFileIO implements UserDAO {
	
	final String USERS_PATH = System.getProperty("user.dir") + File.separator + "users.txt";
	final String TICKETS_PATH = System.getProperty("user.dir") + File.separator + "tickets.txt";
	BufferedReader buffer;
	BufferedWriter writer;
	
	public User getUser(String user_id) {
		try {
			String currentEntry;
			buffer = new BufferedReader(new FileReader(USERS_PATH));
			while ((currentEntry = buffer.readLine()) != null) {
				String[] user = currentEntry.split("\\s+");
				if (user[0].equals(user_id)) {
					return new User(user[0], user[1], user[2], user[3], user[4]);
				}
			}			
		} catch (FileNotFoundException e) {
			File users = new File(USERS_PATH);
			try {
				users.createNewFile();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean addUser(User user) {
		boolean result = false;
		try {
			writer = new BufferedWriter(new FileWriter(USERS_PATH));
			writer.write(user.getId() + " " + user.getPassword() + " " + user.getFirst_name() + " " + user.getLast_name() + " " + user.getEmail());
			writer.newLine();
			result = true;
		} catch (FileNotFoundException e) {
			File users = new File(USERS_PATH);
			try {
				users.createNewFile();
				addUser(user);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean updateUser(User user) {
		deleteUser(user.getId());
		return addUser(user);
	}
	
	//TODO
	public boolean deleteUser(String user_id) {
		return true;
	}
}
