package ticket.dao;

import ticket.model.Admin;

public interface AdminDAO {
	
	Admin getAdmin(String admin_id);
	
	boolean addAdmin(Admin admin);
	boolean updateAdmin(Admin admin);
	boolean deleteAdmin(String admin_id);	
}
