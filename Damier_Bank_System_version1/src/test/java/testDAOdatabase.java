package test.java;

import Application.AccountHolderInfo;
import ApplicationViews.DoNotHaveAcount;
import Info.DAODdatabase;
import Info.DAOaccountHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class testDAOdatabase {


    DoNotHaveAcount doNotHaveAcount ;
    DAODdatabase daoDdatabase;
    AccountHolderInfo accountHolderInfo;
    DAOaccountHolder daOaccountHolder;


    List<AccountHolderInfo> listOf = new ArrayList<>() ;

@Mock
    Connection connection;

@Mock
    Statement statement;

@Mock
    PreparedStatement preparedStatement;



@Mock
    DAOaccountHolder file;





    @BeforeEach

    public  void init() throws IOException {

      //  doNotHaveAcount = new DoNotHaveAcount();
        daoDdatabase = new DAODdatabase();
        accountHolderInfo = new AccountHolderInfo();

        Mockito.mock(DAOaccountHolder.class);


        FileWriter fw = new FileWriter("Testing");
        BufferedWriter writer = new BufferedWriter(fw);

        writer.write("Writting some test");
        writer.flush();
        writer.close();

daOaccountHolder = new DAOaccountHolder("Testing");
    }



    @Test

    public  void testArraylistWithTypeofAccountHolder(){

        accountHolderInfo.setName("Ray");
        accountHolderInfo.setAddress("streeyt");



        listOf.add(accountHolderInfo);
        listOf.add(accountHolderInfo);


        Assertions.assertEquals(2,listOf.size());


    }

    @Test
    public void testObject() throws IOException {

        Assertions.assertNotNull(daOaccountHolder);
    }


@Test


    public void testAcctGenerator(){

        Object random = doNotHaveAcount.accountGenerator();

Assertions.assertNotSame(random,doNotHaveAcount.accountGenerator());

}


}
