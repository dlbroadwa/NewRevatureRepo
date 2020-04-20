package data;

import models.InstrumentModel;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Scanner;

import static org.junit.Assert.*;

public class InstrumentSQLRepositoryTest {

    InstrumentModel testInstrument;


    @Test
    public void setId()
    {
        this.testInstrument = new InstrumentModel();
        Scanner scanner = new Scanner(String.valueOf(4));
        this.testInstrument.setId(scanner.nextInt());
        assertEquals(4, this.testInstrument.getId());
    }

    @Test
    public void setInstrumentName()
    {
        this.testInstrument = new InstrumentModel();
        Scanner scanner = new Scanner("Buffet Crampon");
        this.testInstrument.setInstrumentName(scanner.nextLine());
        assertEquals("Buffet Crampon", this.testInstrument.getInstrumentName());
    }

    @Test
    public void setUsed()
    {
        this.testInstrument = new InstrumentModel();
        Scanner scanner = new Scanner(String.valueOf(0));
        this.testInstrument.setUsed(scanner.nextInt());
        assertEquals(0, this.testInstrument.getUsed());
    }

    @Test
    public void setPrice()
    {
        this.testInstrument = new InstrumentModel();
        Scanner scanner = new Scanner(String.valueOf(0f));
        this.testInstrument.setPrice(0f);
        assertTrue(this.testInstrument.getPrice() == scanner.nextFloat());
    }
}