package ticket.screen;

import ticket.app.Application;

/**
 * Screen --- Outputs the display, then passes to a new Screen when it is done.
 * @author Austin Kind
 */
public interface Screen {
	
	/**
	 * Displays the Screen then returns the next Screen.
	 * @param app	The application running.
	 * @return 		The next Screen to be displayed.
	 */
	Screen doScreen(Application app);
}
