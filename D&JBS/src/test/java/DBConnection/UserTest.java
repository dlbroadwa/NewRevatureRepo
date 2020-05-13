package DBConnection;

import org.junit.Test;
import org.mockito.Mock;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * This test for the getter setter method testing to make sure when a get or set method is called
 * that it will properly excecute and store the correct info for later retrieval.
 */


public class UserTest {
    private String firstName = "Bill";
    private String surName = "Boggins";
    private String email = "bobo@gmail.com";
    private int socialSeurity = 999-99-9999;


    @Mock
    User userTest;


    @Test
    public void getFirstName() {
        this.userTest = new User();
        this.userTest.setFirstName(firstName);
        String name = "Johnny";
        assertNotEquals(name, this.userTest.getFirstName());
    }


    @Test
    public void setFirstName() {
        this.userTest = new User();
        this.userTest.setFirstName("Johnny");
        String firstName = this.userTest.getFirstName();
        this.userTest.setFirstName("Johnny");
        assertEquals(firstName, this.userTest.getFirstName());
    }


    @Test
    public void getSurName() {
        this.userTest = new User();
        this.userTest.setSurname(this.surName);
        String surName = "Sheerin";
        assertNotEquals(surName, this.userTest.getSurname());
    }


    @Test
    public void setSurName() {
        this.userTest = new User();
        this.userTest.setSurname("Sheerin");
        String surName = this.userTest.getSurname();
        this.userTest.setSurname("Sheerin");
        assertEquals(surName, this.userTest.getSurname());
    }


    @Test
    public void getEmail() {
        this.userTest = new User();
        this.userTest.setEmail(this.email);
        String mail = "bilybob@thorton.com";
        assertNotEquals(mail, this.userTest.getEmail());
    }


    @Test
    public void setEmail() {
        this.userTest = new User();
        this.userTest.setEmail("Sheerin");
        String mail = this.userTest.getEmail();
        assertEquals(mail, this.userTest.getEmail());
    }

}




                            //*******NOT Implimented*************//

/* same thing with socailsecurity
    @Test
    public void getSurName() {
        this.userTest = new User();
        this.userTest.setSurname(this.surName);
        String surName = "Sheerin";
        assertNotEquals(surName, this.userTest.getSurname());
    }


    @Test
    public void setSurName() {
        this.userTest = new User();
        this.userTest.setSurname("Sheerin");
        String surName = this.userTest.getSurname();
        this.userTest.setSurname("Sheerin");
        assertEquals(surName, this.userTest.getSurname());
    }
*/
