/**
 * The InstrumentModelTest tests if the function within can assign/get
 * the properties.
 */
package models;

import org.junit.Test;
import org.mockito.Mock;
import static org.junit.Assert.*;

public class InstrumentModelTest 
{
    private int id = 1;
    private String instrumentName = "Buffet";
    private int used = 0;
    private float price = 5000f;
    
    @Mock
    InstrumentModel testIM;


    // Tests if all the properties in the InstrumentModel Object was assigned.
    @Test
    public void testToString() 
    {
        this.testIM = new InstrumentModel();
        testIM.setId(id);
        testIM.setInstrumentName(instrumentName);
        testIM.setUsed(0);
        testIM.setPrice(price);
        assertTrue(testIM.getId() == this.id);
        assertTrue(testIM.getInstrumentName().equals(this.instrumentName));
        assertTrue(testIM.getUsed() == this.used);
        assertTrue(testIM.getPrice() == this.price);
    }


    // Tests if the Id assigned to the InstrumentModel
    @Test
    public void getId() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setId(this.id);
        assertEquals(id, this.testIM.getId());
    }


    // Tests if the old Id is changed to the new one
    @Test
    public void setId() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setId(2);
        int oldId = this.testIM.getId();
        this.testIM.setId(3);
        assertTrue(oldId != this.testIM.getId());
    }


    // Checks to see if the Instrument name property was properly retrieved.
    @Test
    public void getInstrumentName() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setInstrumentName(this.instrumentName);
        assertEquals(instrumentName, this.testIM.getInstrumentName());
    }


    // Checks if the instrumentModel name is equal to an expected Instrument name.
    @Test
    public void setInstrumentName() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setInstrumentName("Yamaha");
        String oldInstrumentName = this.testIM.getInstrumentName();
        this.testIM.setInstrumentName("Yamaha");
        assertEquals(oldInstrumentName , (this.testIM.getInstrumentName()));
    }


    // Checks if the used property is properly retrieved.
    @Test
    public void getUsed() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setUsed(this.used);
        assertEquals(used, this.testIM.getUsed());
    }


    // Checks if the used value has changed.
    @Test
    public void setUsed() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setUsed(0);
        int oldUsedState = this.testIM.getId();
        this.testIM.setUsed(1);
        assertTrue(oldUsedState != this.testIM.getUsed());
    }


    // Checks to see of the price property is properly retrieved.
    @Test
    public void getPrice() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setPrice(this.price);
        assertTrue(price == this.testIM.getPrice());
    }


    // Checks to see if the price was properly changed.
    @Test
    public void setPrice() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setPrice(3000);
        int oldPrice = this.testIM.getId();
        this.testIM.setPrice(4000);
        assertTrue(oldPrice != this.testIM.getPrice());
    }
}