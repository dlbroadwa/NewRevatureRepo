package MyServlets;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the input during the register form user fill out.
 * the fields must be completed with the correct type
 * certain fields need to be a specific length
 *
 *
 */

public class RegisterValidatorTest {

    private static String SSN = "999999999";
    private String firstName = "Johnny";
    private String surname = "Sheerin";
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


    //*********************** Tests input for SSN *********************//
    @Test
    public void socialCheck() {
        if (this.SSN != null) {
            checkSocial = true;
        }
        assertTrue(checkSocial);
    }

    //********************  Tests SSN = 9 characters**************//
    @Test
    public void socialLengthCheck() {
        int expectedSocial = 9;
        Assert.assertEquals(expectedSocial, this.SSN.length());
    }

    // ******************* Test input for first / last name *******//
    @Test
    public void nameCheck() {
        if (this.firstName != null && this.surname != null) {
            checkName = true;
        }
        assertTrue(checkName);
    }

    //************************ Tests adress input ***************//
    @Test
    public void addressCheck() {
        if (this.StreetAddress != null && this.City != null && this.state != null && this.zipCode != null) {
            checkAddress = true;
        }
        assertTrue("incomplete address field", checkAddress);
    }

    //**************** Tests Zip code length *************************//
    @Test
    public void zipLengthCheck() {
        int expectedZipLength = 5;
        Assert.assertEquals(expectedZipLength, this.zipCode.length());
    }


    //*************** Checks phone number input *********************//
    @Test
    public void phoneCheck() {
        if (this.phoneNumber != null) {
            checkPhone = true;
        }
        assertTrue(checkPhone);
    }

    //**************** Tests Phone number length *************************//
    @Test
    public void phoneLengthCheck() {
        int expectedPhoneLength = 10;
        Assert.assertEquals(expectedPhoneLength, this.phoneNumber.length());
    }

    //************ Tests Account number input *************************//
    @Test
    public void accountCheck() {
        if (this.AccountNumber != null) {
            checkAccount = true;
        }
        assertTrue(checkAccount);
    }

    //*********** Tests Gender input *************************//
    @Test
    public void genderCheck() {
        if (this.gender == "male" || this.gender == "female") {
            checkGender = true;
        }
        assertTrue(checkGender);
    }


}


