package ticket.dao;

import java.util.List;

import ticket.model.User;

public interface UserDAO {
	
	User getUser(String user_id);
	List<String> getEmails();
	boolean addUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(String user_id);	
}
