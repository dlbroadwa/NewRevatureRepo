package ticket.utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	
	public static String encrypt(String plaintext) {
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
	
	public static void main(String[] args) {
		System.out.println("plain: jumboshrimp");
		System.out.println("cipher: " + encrypt("jumboshrimp"));
	}
}
