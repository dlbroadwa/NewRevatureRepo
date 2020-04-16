package ticket.dao;

import ticket.model.Admin;

public interface AdminDAO {
	
	Admin getAdmin(String admin_id);

	boolean updateAdmin(Admin admin);
}
