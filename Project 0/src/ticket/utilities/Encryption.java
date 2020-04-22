package ticket.utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encryption --- Encrypts a given string using SHA-512.
 * @author Austin Kind
 */
public class Encryption {
	
	/**
	 * Encrypts a given string using SHA-512.
	 * @param plaintext		The string needing to be encrypted.
	 * @return 				Encrypted string.
	 */
	public static String encrypt(String plaintext) {
		if (plaintext == null)
			return null;
		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = md.digest(plaintext.getBytes());
			BigInteger num = new BigInteger(1, bytes);
			ciphertext = num.toString(16);
			while (ciphertext.length() < 32) {
				ciphertext = "0" + ciphertext;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return ciphertext;
	}
}