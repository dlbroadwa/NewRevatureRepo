

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import classes.*;
import services.*;

class test {
	
	@Test
	public void noNullFromCreatorService() {
		
		CreatorService creator = new CreatorService(new InputManager(), new Scanner(System.in));
	
		assertNotNull(creator.create());
		
	}
	
	@Test
	public void notNullFromDatabaseManager() {
		/* Also testing inheritances*/
		
		DatabaseManager db = new DatabaseManager(new InputManager(), new Scanner(System.in));
		
		assertNotNull(db.getResult("ItemNum < 2000"));
		
	}
	
	@Test
	public void testingValidateInput() {
		
		InputManager handler= new InputManager();
		
		assertEquals(3, handler.iputValidateString("3", new Scanner(System.in), 1, 3));
		
	}
	
	@Test
	public void testingGetterAndSetterForHat() {
		/* Also testing inheritances*/
		
		Hat hat = new Hat();
		
		hat.setColor("Green");
		
		assertEquals("Green", hat.getColor());
		
	}
	
	@Test
	public void testingGetterAndSetterForPants() {
		
		Pants pants = new Pants();
		
		pants.setPantLength("shorts");
		
		assertEquals("shorts", pants.getPantLength());
		
	}
	
	@Test
	public void testingGetterAndSetterForShirt() {
		
		Shirt shirt = new Shirt();
		
		shirt.setSleeveLength("Long-sleeved");
		
		assertEquals("Long-sleeved", shirt.getSleeveLength());
		
	}
	
	@Test
	public void testingToStringForShirt() {
		
		Shirt shirt = new Shirt();
		
		shirt.setColor("Green");
		
		shirt.setSize("s");
		
		shirt.setSleeveLength("Long-sleeved");
		
		assertEquals("s" + " " + "Green" + " " + "Long-sleeved" + " " + "shirt", shirt.toString());
		
	}
	
	@Test
	public void testingToStringForHat() {
		
		Hat hat = new Hat();
		
		hat.setColor("Purple");
		
		hat.setSize("xl");
		
		assertEquals("xl" + " " + "Purple" + " " + "hat", hat.toString());
		
	}
	
	@Test
	public void testingToStringForPants() {
		
		Pants pants = new Pants();
		
		pants.setColor("Orange");
		
		pants.setSize("m");
		
		pants.setPantLength("shorts");
		
		assertEquals("m" + " " + "Orange" + " " + "shorts", pants.toString());
		
	}

}
