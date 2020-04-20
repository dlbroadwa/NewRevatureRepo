package admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AdminTest
{
    private int id;
    private int verification;


    @Before
    public void setId()
    {
        this.id = 224456789;
    }

    @Test
    public void testIdVerification()
    {
        Scanner scanner = new Scanner(String.valueOf(224456789));
        assertTrue(this.id == scanner.nextInt());
    }
}