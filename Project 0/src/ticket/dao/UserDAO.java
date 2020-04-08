package ticket.dao;

import ticket.User;

public interface UserDAO {
	
	User getUser(String user_id);
	
	boolean addUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(User user);	
}
