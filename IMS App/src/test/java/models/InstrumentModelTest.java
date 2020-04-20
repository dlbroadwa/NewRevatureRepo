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

    @Test
    public void getId() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setId(this.id);
        assertTrue(id == this.testIM.getId());
    }

    @Test
    public void setId() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setId(2);
        int oldId = this.testIM.getId();
        this.testIM.setId(3);
        assertTrue(oldId != this.testIM.getId());
    }

    @Test
    public void getInstrumentName() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setInstrumentName(this.instrumentName);
        assertTrue(instrumentName.equals(this.testIM.getInstrumentName()));
    }

    @Test
    public void setInstrumentName() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setInstrumentName("Yamaha");
        String oldInstrumentName = this.testIM.getInstrumentName();
        this.testIM.setInstrumentName("Buffet");
        assertTrue(oldInstrumentName != (this.testIM.getInstrumentName()));
    }

    @Test
    public void getUsed() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setUsed(this.used);
        assertTrue(used == this.testIM.getUsed());
    }

    @Test
    public void setUsed() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setUsed(0);
        int oldUsedState = this.testIM.getId();
        this.testIM.setUsed(1);
        assertTrue(oldUsedState != this.testIM.getUsed());
    }

    @Test
    public void getPrice() 
    {
        this.testIM = new InstrumentModel();
        this.testIM.setPrice(this.price);
        assertTrue(price == this.testIM.getPrice());
    }

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