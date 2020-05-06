package gradebook;

import gradebook.app.Application;
import gradebook.app.GradeBookApplication;
import gradebook.services.LoginService;

public class Main {
	
	public static void main (String[] args) {
		
		LoginService ls = new LoginService();
		ls.validate("test", "pass");
		Application app = new GradeBookApplication();
		app.run();
	}
}
