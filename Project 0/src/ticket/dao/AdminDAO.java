package ticket.dao;

import ticket.Admin;

public interface AdminDAO {
	
	Admin getAdmin(String admin_id);
	
	boolean addAdmin(Admin admin);
	boolean updateAdmin(Admin admin);
	boolean deleteAdmin(Admin admin);	
}
