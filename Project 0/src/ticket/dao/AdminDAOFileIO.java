package ticket.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ticket.model.Admin;

public class AdminDAOFileIO implements AdminDAO {
	
	final String ADMINS_PATH = System.getProperty("user.dir") + File.separator + "admins.txt";
	BufferedReader buffer;
	BufferedWriter writer;
	
	public Admin getAdmin(String admin_id) {
		try {
			String currentEntry;
			buffer = new BufferedReader(new FileReader(ADMINS_PATH));
			while ((currentEntry = buffer.readLine()) != null) {
				String[] admin = currentEntry.split("\\s+");
				if (admin[0].equals(admin_id)) {
					return new Admin(admin[0], admin[1], admin[2], admin[3], admin[4]);
				}
			}			
		} catch (FileNotFoundException e) {
			File users = new File(ADMINS_PATH);
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
	
	public boolean addAdmin(Admin admin) {
		boolean result = false;
		try {
			writer = new BufferedWriter(new FileWriter(ADMINS_PATH));
			writer.write(admin.getId() + " " + admin.getPassword() + " " + admin.getFirst_name() + " " + admin.getLast_name() + " " + admin.getEmail());
			writer.newLine();
			result = true;
		} catch (FileNotFoundException e) {
			File users = new File(ADMINS_PATH);
			try {
				users.createNewFile();
				addAdmin(admin);
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
	
	public boolean updateAdmin(Admin admin) {
		deleteAdmin(admin.getId());
		return addAdmin(admin);
	}
	
	//TODO
	public boolean deleteAdmin(String admin_id) {
		return true;
	}
}
