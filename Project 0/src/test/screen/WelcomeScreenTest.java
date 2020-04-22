package test.screen;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import ticket.app.TicketApplication;

public class WelcomeScreenTest {

	@Mock
	TicketApplication mockTicketApp;
	
	@Mock
	Scanner mockScanner;
	
	@Before
	public void setup() {
		when(mockTicketApp.getScanner()).thenReturn(mockScanner);
		when(mockScanner.hasNextInt()).thenReturn(true);
		when(mockScanner.nextInt()).thenReturn(1);
		doNothing().when(mockScanner.nextLine());
	}
	
	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRegister() {
		
	}
	
	@Test
	public void testExit() {
		
	}

}
