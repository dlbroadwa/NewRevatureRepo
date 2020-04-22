package test.java;

import Application.DispatchingTask;
import ApplicationViews.AccountHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class testAccountHolder {
    DispatchingTask task = new DispatchingTask("Something");
    AccountHolder accountHolder ;

    double expected =3.0;
    int counter =1;

   @BeforeEach
    public void initToGenarateInstanceForEachMethod(){

        accountHolder = new AccountHolder(task);

       ++counter;
    }

    @Test
    public void testWithdraw(){

     double val =   accountHolder.withdraw(30 );


       Assertions.assertNotSame(val,val);

    }

    @Test
    public void testDeposit(){

       double amount =10;

        double val =   accountHolder.deposit(amount );

       Assertions.assertNotEquals(0,val);

        System.out.println("Number of Test " + counter++ );
    }





}
