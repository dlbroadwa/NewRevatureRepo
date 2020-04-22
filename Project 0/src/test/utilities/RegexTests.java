package test.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

import ticket.utilities.Regex;

public class RegexTests {

	@Test
	public void returnTrueIfUserIdIsValid() {
		assertEquals(true, Regex.isValidUserID("TESTtestTEST"));
		assertEquals(true, Regex.isValidUserID("try"));
		assertEquals(true, Regex.isValidUserID("T_162_d"));
		assertEquals(true, Regex.isValidUserID("Z_________0"));
	}
	
	@Test
	public void returnFalseIfUserIdIsInvalid() {
		assertEquals(false, Regex.isValidUserID("tr"));
		assertEquals(false, Regex.isValidUserID("TESTtestTEST1"));
		assertEquals(false, Regex.isValidUserID("12345"));
		assertEquals(false, Regex.isValidUserID("_A123"));
		assertEquals(false, Regex.isValidUserID("Anat!"));
		assertEquals(false, Regex.isValidUserID("Big Space"));
		assertEquals(false, Regex.isValidUserID(null));
	}

	@Test
	public void returnTrueIfPasswordIsValid() {
		assertEquals(true, Regex.isValidPassword("123456"));
		assertEquals(true, Regex.isValidPassword("12345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678"));
		assertEquals(true, Regex.isValidPassword("abc123!@#$%^&*()[]~`<>,./?;'|_"));
	}
	
	@Test
	public void returnFalseIfPasswordIsInvalid() {
		assertEquals(false, Regex.isValidPassword("12345"));
		assertEquals(false, Regex.isValidPassword("123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781"));
		assertEquals(false, Regex.isValidPassword("123 456"));
		assertEquals(false, Regex.isValidPassword("	A123"));
		assertEquals(false, Regex.isValidPassword("Anat! "));
		assertEquals(false, Regex.isValidPassword(null));
	}
	
	@Test
	public void returnTrueIfNameIsValid() {
		assertEquals(true, Regex.isValidName("t"));
		assertEquals(true, Regex.isValidName("TestTestTestTestTest"));
		assertEquals(true, Regex.isValidName("simpleName"));
	}
	
	@Test
	public void returnFalseIfNameIsInvalid() {
		assertEquals(false, Regex.isValidName(""));
		assertEquals(false, Regex.isValidName("TestTestTestTestTestT"));
		assertEquals(false, Regex.isValidName("Number1"));
		assertEquals(false, Regex.isValidName("bad_name"));
		assertEquals(false, Regex.isValidName("BadName!"));
		assertEquals(false, Regex.isValidName("very bad name"));
		assertEquals(false, Regex.isValidName(null));
	}
	
	@Test
	public void returnTrueIfEmailIsValid() {
		assertEquals(true, Regex.isValidEmail("sample@gmail.com"));
		assertEquals(true, Regex.isValidEmail("this.is.a.real.email@hotmail.com"));
		assertEquals(true, Regex.isValidEmail("valid@structure.yes"));
	}
	
	@Test
	public void returnFalseIfEmailIsInvalid() {
		assertEquals(false, Regex.isValidEmail(""));
		assertEquals(false, Regex.isValidEmail("1!@#!$1234123$!@3#%$%`@gmail.com"));
		assertEquals(false, Regex.isValidEmail("dongmail.com"));
		assertEquals(false, Regex.isValidEmail("don@gmailcom"));
		assertEquals(false, Regex.isValidEmail("don@yahoo.comcomcom"));
		assertEquals(false, Regex.isValidEmail(null));
	}
	
	@Test
	public void returnTrueIfIsWhitespace() {
		assertEquals(true, Regex.isWhitespace(" "));
		assertEquals(true, Regex.isWhitespace("\t"));
		assertEquals(true, Regex.isWhitespace("\n"));
	}
	
	@Test
	public void returnFalseIfIsNotWhitespace() {
		assertEquals(false, Regex.isWhitespace(""));
		assertEquals(false, Regex.isWhitespace("test space"));
		assertEquals(false, Regex.isWhitespace(null));
	}
}
