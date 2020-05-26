package utils;

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
	
	/**
	 * Returns true if user_id matches the regex for a valid user ID.
	 * @param user_id	A user id.
	 * @return 			True if matches pattern.
	 */
	public static boolean isValidUserID(String user_id) {
		if (user_id == null)
			return false;
		Matcher validString = USER_REGEX.matcher(user_id);
		return validString.matches();
	}
	
	/**
	 * Returns true if password matches the regex for a valid password.
	 * @param password	A password.
	 * @return 	True if matches pattern.
	 */
	public static boolean isValidPassword(String password) {
		if (password == null)
			return false;
		Matcher validString = PASSWORD_REGEX.matcher(password);
		return validString.matches();
	}
	
	/**
	 * Returns true if name matches the regex for a valid name.
	 * @param name	A name.
	 * @return 	True if matches pattern.
	 */
	public static boolean isValidName(String name) {
		if (name == null)
			return false;
		Matcher validString = NAME_REGEX.matcher(name);
		return validString.matches();
	}
}
