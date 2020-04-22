package ticket.dao;

import java.util.List;

import ticket.model.User;

/**
 * UserDAO --- Accesses data from a users table.
 * @author Austin Kind
 */
public interface UserDAO {
	
	/**
	 * Returns the User of the given user_id if it exists.
	 * @param user_id	The user ID to return.
	 * @return 			The user who has the given user ID.
	 */
	User getUser(String user_id);
	
	/**
	 * Retrieves all the tickets from the given user ID and returns them in a list.
	 * @return 			A list of emails from the users table.
	 */
	List<String> getEmails();
	
	/**
	 * Adds the given User to the table.
	 * @param user		The user to be added.
	 * @return 			True if operation was successful.
	 */
	boolean addUser(User user);
	
	/**
	 * Updates the given User to the table.
	 * @param user		The user to be updated.
	 * @return 			True if operation was successful.
	 */
	boolean updateUser(User user);
	
	/**
	 * Deletes the given User from the table.
	 * @param user_id	The ID of the user to be deleted.
	 * @return 			True if operation was successful.
	 */
	boolean deleteUser(String user_id);	
}
