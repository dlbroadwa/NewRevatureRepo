package ticket.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

	static final Pattern USER_REGEX = Pattern.compile("^[a-zA-Z]{1}\\w{2,11}$");
	static final Pattern PASSWORD_REGEX = Pattern.compile("^\\S{6,128}$");
	static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-Z]{1,20}$");
	static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}");
	
	public static boolean isValidUserID(String user_id) {
		Matcher validString = USER_REGEX.matcher(user_id);
		if (validString.matches())
			return true;
		else
			return false;
	}
	
	public static boolean isValidPassword(String password) {
		Matcher validString = PASSWORD_REGEX.matcher(password);
		if (validString.matches())
			return true;
		else
			return false;
	}
	
	public static boolean isValidName(String name) {
		Matcher validString = NAME_REGEX.matcher(name);
		if (validString.matches())
			return true;
		else
			return false;
	}
	
	public static boolean isValidEmail(String email) {
		Matcher validString = EMAIL_REGEX.matcher(email);
		if (validString.matches())
			return true;
		else
			return false;
	}
}
