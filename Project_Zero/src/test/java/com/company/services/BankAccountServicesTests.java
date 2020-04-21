package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.banking.Account;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

public class BankAccountServicesTests {
    private Account account = new Account(12345, 0, new ArrayList<>());


    @Mock
    private BankAccountDAO bankAccountDAO = null;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void accountExistsNullTest() {
        Mockito.when(bankAccountDAO.retrieveByID(account.getAccountID())).thenReturn(null);

        Assert.assertEquals(false, BankAccountServices.accountExists(account.getAccountID(), bankAccountDAO));
    }

    @Test
    public void accountExistsEmptyArrayTest() {
        Mockito.when(bankAccountDAO.retrieveByID(account.getAccountID())).thenReturn(new Account[]{});

        Assert.assertEquals(false, BankAccountServices.accountExists(account.getAccountID(), bankAccountDAO));
    }

    @Test
    public void accountExistsTest() {
        Mockito.when(bankAccountDAO.retrieveByID(account.getAccountID())).thenReturn(new Account[]{account});

        Assert.assertEquals(true, BankAccountServices.accountExists(account.getAccountID(), bankAccountDAO));
    }
}
