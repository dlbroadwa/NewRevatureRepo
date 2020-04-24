package gradebook.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex --- Establishes various regular expressions and compares them to given strings.
 * @author Austin Kind
 */
public class Regex {

	private static final Pattern USER_REGEX = Pattern.compile("^[a-zA-Z]{1}\\w{2,11}$");
	private static final Pattern PASSWORD_REGEX = Pattern.compile("^\\S{6,128}$");
	private static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-Z]{1,20}$");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
	private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w!#$%&*+/=?`{|}~^-]+(?:\\.[\\w!#$%&*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}");
	
	/**
	 * Returns true if user_id matches the regex for a valid user ID.
	 * @param user_id	A user id.
	 * @return 			True if matches pattern.
	 */
	public static boolean isValidUserID(String user_id) {
		if (user_id == null)
			return false;
		Matcher validString = USER_REGEX.matcher(user_id);
		if (validString.matches())
			return true;
		else
			return false;
	}
	
	/**
	 * Returns true if password matches the regex for a valid password.
	 * @param password	A password.
	 * @return 			True if matches pattern.
	 */
	public static boolean isValidPassword(String password) {
		if (password == null)
			return false;
		Matcher validString = PASSWORD_REGEX.matcher(password);
		if (validString.matches())
			return true;
		else
			return false;
	}
	
	/**
	 * Returns true if name matches the regex for a valid name.
	 * @param user_id	A name.
	 * @return 			True if matches pattern.
	 */
	public static boolean isValidName(String name) {
		if (name == null)
			return false;
		Matcher validString = NAME_REGEX.matcher(name);
		if (validString.matches())
			return true;
		else
			return false;
	}
	
	/**
	 * Returns true if email matches the regex for a valid email.
	 * @param email 	An email.
	 * @return 			True if matches pattern.
	 */
	public static boolean isValidEmail(String email) {
		if (email == null)
			return false;
		Matcher validString = EMAIL_REGEX.matcher(email);
		if (validString.matches())
			return true;
		else
			return false;
	}
}