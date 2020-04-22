package test.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

import ticket.utilities.Encryption;

public class EncryptionTests {

	@Test
	public void testEncryption() {
		String str = "example";
		assertEquals(Encryption.encrypt(new String("test")), Encryption.encrypt(new String("test")));
		assertEquals(Encryption.encrypt(str), Encryption.encrypt(str));
		assertEquals(Encryption.encrypt(""), Encryption.encrypt(""));
		assertNotEquals(Encryption.encrypt(""), Encryption.encrypt(" "));
		assertNotEquals(Encryption.encrypt(null), Encryption.encrypt("test"));
	}

}
