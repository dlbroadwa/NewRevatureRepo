package Controler;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class AccountHolderPerSonalInfoTest {
    private static String SSN = "999999999";
    private String firstName = "Johnny";
    private String Lastname = "Sheerin";
    private String email = "johnny.sheerin.is@gmail.com";
    private String StreetAddress = "666 Goa Way";
    private String City = "Truckee";
    private String state = "CA";
    private String zipCode = "66666";
    private String gender = "male";
    private String phoneNumber = "1231231234";
    private String AccountNumber = "123-123-1234";
    private boolean checkPhone;
    private boolean checkSocial;
    private boolean checkName;
    private boolean checkAddress;
    private boolean checkGender;
    private boolean checkAccount;

    @Mock
    AccountHolderPerSonalInfo userTest;


    @Test
    void getFirstName() {
        this.userTest = new AccountHolderPerSonalInfo();
        this.userTest.setFirstName(firstName);
        String name = "Johnny";
    }

    @Test
    void setFirstName() {
        this.userTest = new AccountHolderPerSonalInfo();
        this.userTest.setFirstName("Johnny");
        String firstName = this.userTest.getFirstName();
        this.userTest.setFirstName("Johnny");
        assertEquals(firstName, this.userTest.getFirstName());
    }

    @Test
    void getLastname() {
        this.userTest = new AccountHolderPerSonalInfo();
        this.userTest.setLastname(this.Lastname);
        String lastname = "Sheerin";
    }

    @Test
    void setLastname() {
        this.userTest = new AccountHolderPerSonalInfo();
        this.userTest.setLastname("Sheerin");
        String Lastname = this.userTest.getLastname();
        this.userTest.setLastname("Sheerin");
        assertEquals(Lastname, this.userTest.getLastname());
    }

    @Test
    void getStreetAddress() {
        if (this.StreetAddress != null && this.City != null && this.state != null && this.zipCode != null) {
            checkAddress = true;
        }
        assertTrue("incomplete address field", checkAddress);
    }

    @Test
    void setStreetAddress() {
    }

    @Test
    void getCity() {
        if (this.StreetAddress != null && this.City != null && this.state != null && this.zipCode != null) {
            checkAddress = true;
        }
        assertTrue("incomplete address field", checkAddress);
    }


    @Test
    void setState() {
        if (this.StreetAddress != null && this.City != null && this.state != null && this.zipCode != null) {
            checkAddress = true;
        }
        assertTrue("incomplete address field", checkAddress);
    }

    @Test
    void getZipCode() {
        if (this.StreetAddress != null && this.City != null && this.state != null && this.zipCode != null) {
            checkAddress = true;
        }
        assertTrue("incomplete address field", checkAddress);

        int expectedZipLength = 5;
        Assert.assertEquals(expectedZipLength, this.zipCode.length());
    }

    @Test
    void getSSN() {
        if (this.SSN != null) {
            checkSocial = true;
        }
        assertTrue(checkSocial);


        int expectedSocial = 9;
        Assert.assertEquals(expectedSocial, this.SSN.length());
    }

    @Test
    void getGender() {
        if (this.gender == "male" || this.gender == "female") {
            checkGender = true;
        }
        assertTrue(checkGender);
    }

    @Test
    void getAccountNumber() {
        if (this.AccountNumber != null) {
            checkAccount = true;
        }
        assertTrue(checkAccount);
    }


    @Test
    void getPhoneNumber() {
        if (this.phoneNumber != null) {
            checkPhone = true;
        }
        assertTrue(checkPhone);

        int expectedPhoneLength = 10;
        Assert.assertEquals(expectedPhoneLength, this.phoneNumber.length());
    }
}