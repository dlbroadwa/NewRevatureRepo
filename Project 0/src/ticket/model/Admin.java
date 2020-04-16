package ticket.model;

public class Admin {
	
	private String admin_id;
	private String password;
	private String first_name;
	private String last_name;
	private String email;
	
	public Admin(String admin_id, String password, String first_name, String last_name, String email) {
		this.admin_id = admin_id;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}

	public String getId() {
		return admin_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
