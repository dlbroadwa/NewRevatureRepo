package test.java;

import Application.AccountHolderInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testEmployeeAccountHoldeInfo {

    AccountHolderInfo accountHolderInfo;
    int counter =1;

    @BeforeEach

    public void init(){

        accountHolderInfo = new AccountHolderInfo();

        accountHolderInfo.setAddress("testing Address");
        accountHolderInfo.setBalance(6.99);
        accountHolderInfo.setName("JUnitTest");

        accountHolderInfo.setSocialSecurityNumber(123456789);

        accountHolderInfo.setAccountNumber("3333333333333333");


       ++counter;
    }



    @Test

    public  void testGetAddress(){
        String address = "testing Address";

        Assertions.assertEquals(address,accountHolderInfo.getAddress());

    }


    @Test

    public  void testGetName(){
        String name = "JUnitTest";

        Assertions.assertEquals(name,accountHolderInfo.getName());

    }



    @Test

    public void testSocialSecurity(){

        String SSN = String.valueOf(accountHolderInfo.getSocialSecurityNumber());

        int expected =9;

        Assertions.assertEquals(expected, SSN.length());




    }

    @Test

    public void testAccountNumberLength(){

        String account = accountHolderInfo.getAccountNumber();

        int expected =16;

        Assertions.assertEquals(expected, account.length());


    }

    @Test

    public void testBalance(){

        int Unexpected = 6;
        Assertions.assertEquals(Unexpected, accountHolderInfo.getBalance());

    }

}
