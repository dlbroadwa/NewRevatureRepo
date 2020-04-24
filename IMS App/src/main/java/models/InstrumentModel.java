/**
 * The InstrumentModel Class creates the properties of the Instruments to be sent to
 * the database.
 *
 * functions():
 * toString()- returns a string that includes all the current values of the model.
 * getters- getId(), getInstrumentName(), getUsed(), getPrice()
 * setters- setId(), setInstrumentName(), setUsed(), setPrice()
 * @params setId(), setUsed(), setPrice(): integer value
 * @params setInstrumentName(): String Object
 *
 * NOTE: The used property is set to an int to allow flexibility with determining if the instrument
 * is new, used, or needs to be repaired.
 * [0 new, 1 used, Any other number is an assigned number indicating it needs to be repaired]
 */
package models;

public class InstrumentModel
{
    private int id;
    private String instrumentName;
    private int used;
    private float price;

    public InstrumentModel() {};


    // Returns a String that includes all the properties of the InstrumentModel.
    @Override
    public String toString()
    {
        return "InstrumentModel{"+
                "id=" + id +
                ", name='" + '\'' +
                "used=" + used +
                ",price=" + price+
                '}';
    }


    // Id getter
    public int getId()
    {
        return id;
    }


    // Id setter
    public void setId(int id)
    {
        this.id = id;
    }


    // instrumentName getter
    public String getInstrumentName()
    {
        return instrumentName;
    }


    // instrumentName setter
    public void setInstrumentName(String instrumentName)
    {
        this.instrumentName = instrumentName;
    }


    // used getter
    public int getUsed()
    {
        return used;
    }


    // used setter
    public void setUsed(int used)
    {
        this.used = used;
    }


    // price getter
    public float getPrice()
    {
        return price;
    }


    // price setter
    public void setPrice(float price)
    {
        this.price = price;
    }
}
