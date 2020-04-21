/**
 *  The InstrumentSQLRepositoryTest Class tests the enhanced setter methods within the
 *  InstrumentSQLRepository class.
 *
 *  functions:
 *
 *  setId(): determines if the input passed in the scanner is equal to an expected hard-coded value.
 *
 *  setInstrumentName(): determines if the input passed in the scanner is equal to an expected hard-coded value.
 *
 *  setUsed(): determines if the input passed in the scanner is equal to an expected hard-coded value.
 *
 *  setPrice() determines if the input passed in the scanner is equal to an expected hard-coded value.
 */
package data;

import models.InstrumentModel;
import org.junit.Test;
import java.util.Scanner;
import static org.junit.Assert.*;

public class InstrumentSQLRepositoryTest {

    InstrumentModel testInstrument;


    // Tests if the input in scanner matches the expected value
    @Test
    public void setId()
    {
        this.testInstrument = new InstrumentModel();
        Scanner scanner = new Scanner(String.valueOf(4));
        this.testInstrument.setId(scanner.nextInt());
        assertEquals(4, this.testInstrument.getId());
    }


    // Tests if the input in scanner matches the expected value
    @Test
    public void setInstrumentName()
    {
        this.testInstrument = new InstrumentModel();
        Scanner scanner = new Scanner("Buffet Crampon");
        this.testInstrument.setInstrumentName(scanner.nextLine());
        assertEquals("Buffet Crampon", this.testInstrument.getInstrumentName());
    }


    // Tests if the input in scanner matches the expected value
    @Test
    public void setUsed()
    {
        this.testInstrument = new InstrumentModel();
        Scanner scanner = new Scanner(String.valueOf(0));
        this.testInstrument.setUsed(scanner.nextInt());
        assertEquals(0, this.testInstrument.getUsed());
    }


    // Tests if the input in scanner matches the expected value
    @Test
    public void setPrice()
    {
        this.testInstrument = new InstrumentModel();
        Scanner scanner = new Scanner(String.valueOf(0f));
        this.testInstrument.setPrice(0f);
        assertTrue(this.testInstrument.getPrice() == scanner.nextFloat());
    }
}