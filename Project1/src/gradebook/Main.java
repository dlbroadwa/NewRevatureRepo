package gradebook;

import gradebook.app.Application;
import gradebook.app.GradeBookApplication;

public class Main {
	
	public static void main (String[] args) {
		Application app = new GradeBookApplication();
		app.run();
	}
}
